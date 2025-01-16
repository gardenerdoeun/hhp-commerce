package kr.hhplus.be.server.infrastructure.inMemory;

import kr.hhplus.be.server.domain.order.Order;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderRepository {
    private final Map<Long, Order> orders = new ConcurrentHashMap<>();
    private final AtomicLong IdGenerator = new AtomicLong(0);

    public Order save(Order order) {
        if(order.getOrderId() == null) {
            order.setOrderId(IdGenerator.incrementAndGet());
        }
        orders.put(order.getOrderId(), order);
        return order;
    }
    public Optional<Order> findById(Long orderId) {
        return Optional.ofNullable(orders.get(orderId));
    }
}
