package kr.hhplus.be.server.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter @Setter
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private List<OrderItemInfo> items;
    private double totalAmount;
    private double discountAmount;
    private double finalAmount;
    private String status;

    public OrderResponse(Long orderId, Long userId, List<OrderItemInfo> items, double totalAmount, double discountAmount, double finalAmount, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.items = items;
        this.totalAmount = totalAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.status = status;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderItemInfo> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public String getStatus() {
        return status;
    }


    public static class OrderItemInfo {
        private Long productId;
        private int quantity;
        private int subtotal;

        public OrderItemInfo(Long productId, int quantity, int subtotal) {
            this.productId = productId;
            this.quantity = quantity;
            this.subtotal = subtotal;
        }

        public Long getProductId() {
            return productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public int getSubtotal() {
            return subtotal;
        }
    }
}
