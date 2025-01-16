package kr.hhplus.be.server.domain.coupon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CouponDiscountType {
    FIXED("정액"),
    PERCENT("정률");

    private final String text;
}
