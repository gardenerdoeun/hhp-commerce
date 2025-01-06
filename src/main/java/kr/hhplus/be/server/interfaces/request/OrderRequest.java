package kr.hhplus.be.server.interfaces.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class OrderRequest {
    private Long userId;
    private Long couponId;
    private List<OrderItem> items;

    @Getter @Setter @NoArgsConstructor
    public static class OrderItem {
        private Long productId;
        private int quantity;
    }
}
