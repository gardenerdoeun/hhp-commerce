package kr.hhplus.be.server.interfaces.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class CouponListResponse {
    private Long userId;
    private List<CouponDetail> coupons;

    public CouponListResponse(Long userId, List<CouponDetail> coupons) {
        this.userId = userId;
        this.coupons = coupons;
    }

    @Getter @Setter
    @NoArgsConstructor
    public static class CouponDetail {
        private Long couponId;
        private String couponName;
        private double discountAmount;

        public CouponDetail(Long couponId, String couponName, double discountAmount) {
            this.couponId = couponId;
            this.couponName = couponName;
            this.discountAmount = discountAmount;
        }
    }
}
