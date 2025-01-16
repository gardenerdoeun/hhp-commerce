package kr.hhplus.be.server.domain.order;

import kr.hhplus.be.server.domain.product.ProductService;
import kr.hhplus.be.server.exception.AppException;
import kr.hhplus.be.server.exception.ErrorCode;
import kr.hhplus.be.server.infrastructure.inMemory.OrderDetailRepository;
import kr.hhplus.be.server.infrastructure.inMemory.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderDetailRepository detailRepo;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepo,
                        OrderDetailRepository detailRepo,
                        ProductService productService) {
        this.orderRepo = orderRepo;
        this.detailRepo = detailRepo;
        this.productService = productService;
    }

    public Order createOrder(Long userId, List<OrderItem> items) {
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus(OrderStatus.COMPLETED);
        BigDecimal total = BigDecimal.ZERO;

        orderRepo.save(order);

        for (OrderItem i : items) {
            productService.decreaseStock(i.getProductId(), i.getQuantity());
            BigDecimal unitPrice = productService.getProduct(i.getProductId()).getProductPrice();
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(i.getQuantity()));
            total = total.add(subtotal);

            // OrderDetail
            OrderDetail detail = new OrderDetail();
            detail.setOrderId(order.getOrderId());
            detail.setProductId(i.getProductId());
            detail.setQuantity(i.getQuantity());
            detail.setUnitPrice(unitPrice);
            detail.setSubTotalPrice(subtotal);
            detailRepo.save(detail);
        }

        order.setTotalAmount(total);
        order.setDiscountAmount(BigDecimal.ZERO);
        order.setFinalAmount(total);

        orderRepo.save(order);
        return order;
    }

    public Order getOrder(Long orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new AppException(ErrorCode.BAD_REQUEST, "Order not found"));
    }
}
