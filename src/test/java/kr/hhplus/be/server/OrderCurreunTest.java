package kr.hhplus.be.server;
import kr.hhplus.be.server.application.facade.OrderFacade;
import kr.hhplus.be.server.domain.coupon.CouponService;
import kr.hhplus.be.server.domain.order.OrderItem;
import kr.hhplus.be.server.domain.order.OrderService;
import kr.hhplus.be.server.domain.payment.PaymentService;
import kr.hhplus.be.server.domain.product.ProductService;
import kr.hhplus.be.server.domain.user.UserService;
import kr.hhplus.be.server.external.ExternalDataPlatformSender;
import kr.hhplus.be.server.infrastructure.inMemory.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderConcurrencyTest {

    @Test
    void testConcurrentOrder() throws Exception {

        UserRepository userRepo = new UserRepository();
        WalletRepository walletRepo = new WalletRepository();
        PaymentRepository paymentRepo = new PaymentRepository();
        ProductRepository productRepo = new ProductRepository();
        OrderRepository orderRepo = new OrderRepository();
        OrderDetailRepository detailRepo = new OrderDetailRepository();
        CouponRepository couponRepo = new CouponRepository();
        CouponIssueRepository issueRepo = new CouponIssueRepository();

        // Service
        UserService userService = new UserService(userRepo, walletRepo);
        PaymentService paymentService = new PaymentService(paymentRepo, walletRepo);
        ProductService productService = new ProductService(productRepo);
        OrderService orderService = new OrderService(orderRepo, detailRepo, productService);
        CouponService couponService = new CouponService(couponRepo, issueRepo);

        // Facade
        OrderFacade orderFacade = new OrderFacade(orderService, paymentService, couponService, new ExternalDataPlatformSender());

        // 데이터 준비: 유저, 잔액, 상품/재고, 쿠폰
        var user = userService.createUser("Alice");
        userService.chargeBalance(user.getUserId(), BigDecimal.valueOf(10000));

        var product = productService.createProduct("Pen", 500, "SELLING", 10);

        // 동시 실행
        int threadCount = 5;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for(int i=0; i<threadCount; i++){
            executor.submit(() -> {
                try {
                    OrderItem item = new OrderItem(product.getProductId(), 2);
                    // 할인은 없다고 가정
                    orderFacade.placeOrderAndPay(
                            user.getUserId(),
                            Arrays.asList(item),
                            null,
                            BigDecimal.ZERO,
                            BigDecimal.valueOf(1000)
                    );
                } catch(Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        // 재고 검증 (10개 중 2개씩 5회면 10개가 소진, 예외 없이면 0이어야 함)
        assertEquals(0, productService.getStock(product.getProductId()).getStockQuantity());
    }
}

