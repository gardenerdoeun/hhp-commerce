package kr.hhplus.be.server.domain.product;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ProductStock extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "product_id")
    private Long productId;
    @Column(nullable = false)
    private Integer stockQuantity;

    public void reduceStockQuantity(Integer stockQuantity) throws Exception {
        if(this.stockQuantity - stockQuantity < 0) {
            throw new Exception("재고 부족");
        }
        this.stockQuantity = this.stockQuantity - stockQuantity;
    }
}
