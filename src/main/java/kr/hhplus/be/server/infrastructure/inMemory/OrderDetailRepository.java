package kr.hhplus.be.server.infrastructure.inMemory;

import kr.hhplus.be.server.domain.order.OrderDetail;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class OrderDetailRepository {
    private final Map<Long, OrderDetail> orderDetails = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public OrderDetail save(OrderDetail orderDetail) {
        if(orderDetail.getOrderDetailId() == null) {
            orderDetail.setOrderDetailId(idGenerator.incrementAndGet());
        }
        orderDetails.put(orderDetail.getOrderDetailId(), orderDetail);
        return orderDetail;
    }

    public Optional<OrderDetail> findById(Long orderDetailId) {
        return Optional.ofNullable(orderDetails.get(orderDetailId));
    }
}
