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

    public CouponListResponse(Long userId, List<CouponDetail> coupons) {
        this.userId = userId;
        this.coupons = coupons;
    }

    private List<CouponDetail> coupons;

    public Long getUserId() {
        return userId;
    }

    public List<CouponDetail> getCoupons() {
        return coupons;
    }

    public static class CouponDetail {
        private Long couponId;
        private String couponName;
        private double discountAmount;

        public CouponDetail(Long couponId, String couponName, double discountAmount) {
            this.couponId = couponId;
            this.couponName = couponName;
            this.discountAmount = discountAmount;
        }

        public Long getCouponId() {
            return couponId;
        }

        public String getCouponName() {
            return couponName;
        }

        public double getDiscountAmount() {
            return discountAmount;
        }
    }
}
