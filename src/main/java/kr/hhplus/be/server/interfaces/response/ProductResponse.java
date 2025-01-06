package kr.hhplus.be.server.interfaces.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ProductResponse {
    private Long productId;
    private String productName;
    private double price;
    private int stockQuantity;


    public ProductResponse(Long productId, String productName, double price, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
}
