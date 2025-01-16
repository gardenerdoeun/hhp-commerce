package kr.hhplus.be.server.domain.coupon;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponIssue extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponIssueId;
    private Long couponId;
    private Long userId;
    private String title;
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private CouponIssueType actionType;
    private BigDecimal appliedDiscount;
    private LocalDateTime appliedTime;
}
