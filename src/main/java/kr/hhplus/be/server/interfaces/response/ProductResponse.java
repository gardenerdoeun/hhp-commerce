package kr.hhplus.be.server.interfaces.response;

import kr.hhplus.be.server.domain.product.Product;
import lombok.*;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Builder
@Getter
public class ProductResponse {
    private final Long productId;
    private final String productName;
    private final BigDecimal productPrice;
    private final String productStatus; // 상태를 텍스트로 변환
    private final Integer stockQuantity;

    // 정적 팩토리 메서드
    public static ProductResponse from(Product product, Integer stockQuantity) {
        return ProductResponse.builder()
                .productId(product.getProductId())
                .productName(product.getProductName())
                .productPrice(product.getProductPrice())
                .productStatus(product.getProductName())
                .stockQuantity(stockQuantity)
                .build();
    }
}
