package kr.hhplus.be.server.domain.order;

import static org.junit.jupiter.api.Assertions.*;

import kr.hhplus.be.server.domain.product.Product;
import kr.hhplus.be.server.domain.product.ProductService;
import kr.hhplus.be.server.infrastructure.inMemory.OrderDetailRepository;
import kr.hhplus.be.server.infrastructure.inMemory.OrderRepository;
import kr.hhplus.be.server.infrastructure.inMemory.ProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @Test
    void testCreateOrder() {
        OrderRepository orepo = new OrderRepository();
        OrderDetailRepository drepo = new OrderDetailRepository();
        ProductRepository prepo = new ProductRepository();
        ProductService pservice = new ProductService(prepo);
        OrderService oservice = new OrderService(orepo, drepo, pservice);

        // 상품/재고 생성
        Product product = pservice.createProduct("Notebook", 2000, "SELLING", 5);

        // 주문
        OrderItem item = new OrderItem(product.getProductId(), 2);
        Order order = oservice.createOrder(1L, Collections.singletonList(item), BigDecimal.valueOf(4000), null);
        assertNotNull(order.getOrderId());
        assertEquals(3, pservice.getStock(product.getProductId()).getStockQuantity());

        // 실제 확인
        assertEquals(OrderStatus.COMPLETED, order.getOrderStatus());
    }
}
