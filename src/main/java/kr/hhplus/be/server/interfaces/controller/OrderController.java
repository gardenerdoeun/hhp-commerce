package kr.hhplus.be.server.interfaces.controller;

import kr.hhplus.be.server.interfaces.request.OrderRequest;
import kr.hhplus.be.server.interfaces.response.OrderResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping
    public OrderResponse createOrder(@RequestBody OrderRequest request) {
        double totalAmount = 0.0;
        for (OrderRequest.OrderItem oi : request.getItems()) {
            totalAmount += (10.0 * oi.getQuantity());
        }
        double discountAmount = (request.getCouponId() != null) ? 10.0 : 0.0;
        double finalAmount = totalAmount - discountAmount;

        OrderRequest.OrderItem firstItem = request.getItems().get(0);
        OrderResponse.OrderItemInfo itemInfo = new OrderResponse.OrderItemInfo(
                firstItem.getProductId(),
                firstItem.getQuantity(),
                (10 * firstItem.getQuantity())
        );

        return new OrderResponse(
                9999L, // mock orderId
                request.getUserId(),
                Collections.singletonList(itemInfo),
                totalAmount,
                discountAmount,
                finalAmount,
                "SUCCESS"
        );
    }
}
