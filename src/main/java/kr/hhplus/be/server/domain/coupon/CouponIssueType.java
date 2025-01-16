package kr.hhplus.be.server.domain.coupon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponIssueType {

        USED("사용"),
    UNUSED("미사용");

    private final String text;
}
