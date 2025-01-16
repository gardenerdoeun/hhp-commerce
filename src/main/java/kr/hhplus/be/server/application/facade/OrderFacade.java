package kr.hhplus.be.server.application.facade;

import kr.hhplus.be.server.domain.coupon.CouponService;
import kr.hhplus.be.server.domain.order.Order;
import kr.hhplus.be.server.domain.order.OrderItem;
import kr.hhplus.be.server.domain.order.OrderService;
import kr.hhplus.be.server.domain.payment.Payment;
import kr.hhplus.be.server.domain.payment.PaymentService;
import kr.hhplus.be.server.external.ExternalDataPlatformSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Component
@RequiredArgsConstructor
public class OrderFacade {
    private final OrderService orderService;
    private final PaymentService paymentService;
    private final CouponService couponService;
    private final ExternalDataPlatformSender externalSender;

    public Order placeOrderAndPay(Long userId, List<OrderItem> items,
                                  Long couponIssueId, BigDecimal discount,
                                  BigDecimal payAmount) {

        // (1) 주문 생성
        Order order = orderService.createOrder(userId, items, discount, couponIssueId);

        // (2) 결제
        paymentService.pay(userId, order.getOrderId(), order.getFinalAmount());

        // (3) 쿠폰 사용
        if (couponIssueId != null) {
            couponService.useCoupon(couponIssueId, order.getOrderId(), discount);
        }

        // (4) 외부 전송
        externalSender.sendOrderInfo(order);

        return order;
    }
}
