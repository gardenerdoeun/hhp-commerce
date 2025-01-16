package kr.hhplus.be.server.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.application.facade.OrderFacade;
import kr.hhplus.be.server.domain.order.Order;
import kr.hhplus.be.server.domain.order.OrderItem;
import kr.hhplus.be.server.interfaces.request.OrderRequest;
import kr.hhplus.be.server.interfaces.response.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Tag(name = "Order", description= "주문 관련 API")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderFacade orderFacade;

    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @Operation(summary = "주문/결제", description = "주문/결제 요청")
    @PostMapping
    public Order placeOrder(@RequestParam Long userId,
                            @RequestBody List<OrderItem> items,
                            @RequestParam(required=false) Long couponIssueId,
                            @RequestParam(required=false) BigDecimal discount,
                            @RequestParam BigDecimal payAmount) {
        return orderFacade.placeOrderAndPay(userId, items, couponIssueId, discount, payAmount);
    }
}
