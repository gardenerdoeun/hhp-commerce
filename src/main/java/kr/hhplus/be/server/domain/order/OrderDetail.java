package kr.hhplus.be.server.domain.order;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.BaseEntity;
import kr.hhplus.be.server.domain.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;


@Entity
@Getter @Setter
@NoArgsConstructor
public class OrderDetail extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderDetailId;

//    @ManyToOne(fetch = FetchType.LAZY)
    private Long orderId;
//    @ManyToOne(fetch = FetchType.LAZY)
    private Long productId;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal subTotalPrice;
}
