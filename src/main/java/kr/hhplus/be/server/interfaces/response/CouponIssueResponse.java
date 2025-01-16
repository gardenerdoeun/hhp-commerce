package kr.hhplus.be.server.interfaces.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CouponIssueResponse {
    private Long userId;
    private String couponName;
    private String message;

    public CouponIssueResponse(long userId, String couponName, String message) {
        this.userId = userId;
        this.couponName = couponName;
        this.message = message;
    }
}
