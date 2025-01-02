package kr.hhplus.be.server.interfaces.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CouponRequest {
    private Long couponId;
    private String couponName;

    public Long getCouponId() {
        return couponId;
    }

    public String getCouponName() {
        return couponName;
    }
}
