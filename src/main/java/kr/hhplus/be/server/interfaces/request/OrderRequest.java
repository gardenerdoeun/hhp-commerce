package kr.hhplus.be.server.interfaces.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

public class OrderRequest {
    private Long userId;
    private Long couponId;
    private List<OrderItem> items;

    public Long getUserId() {
        return userId;
    }

    public Long getCouponId() {
        return couponId;
    }

    public List<OrderItem> getItems() {
        return items;
    }


    public static class OrderItem {
        private Long productId;
        private int quantity;

        public Long getProductId() {
            return productId;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
