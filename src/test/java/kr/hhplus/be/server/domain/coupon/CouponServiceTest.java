package kr.hhplus.be.server.domain.coupon;

import kr.hhplus.be.server.infrastructure.inMemory.CouponIssueRepository;
import kr.hhplus.be.server.infrastructure.inMemory.CouponRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CouponServiceTest {

    @Test
    void testIssueAndUseCoupon() {
        CouponRepository couponRepository = new CouponRepository();
        CouponIssueRepository couponIssueRepository = new CouponIssueRepository();
        CouponService service = new CouponService(couponRepository, couponIssueRepository);

        // 쿠폰 등록
        Coupon coupon = new Coupon();
        coupon.setCouponName("TestCoupon");
        coupon.setDiscountType(CouponDiscountType.FIXED);
        coupon.setDiscountValue(BigDecimal.valueOf(500));
        coupon.setQuantity(2);
        couponRepository.save(coupon);

        // 발급
        CouponIssue ci = service.issueCoupon(coupon.getCouponId(), 10L);
        assertNotNull(ci.getCouponIssueId());
        assertEquals(1, coupon.getQuantity()); // 2 -> 1 남음

        // 사용
        service.useCoupon(ci.getCouponIssueId(), 100L, BigDecimal.valueOf(500));
        assertEquals(CouponIssueType.USED, ci.getActionType());
    }
}
