package kr.hhplus.be.server.interfaces.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class HitProductResponse {
    private Long productId;
    private String productName;
    private String saleCount;

    public HitProductResponse(long productId, String productName, int saleCount) {
        this.productId = productId;
        this.productName = productName;
        this.saleCount = String.valueOf(saleCount);
    }
}
