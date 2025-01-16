package kr.hhplus.be.server.domain.coupon;

import kr.hhplus.be.server.infrastructure.inMemory.CouponIssueRepository;
import kr.hhplus.be.server.infrastructure.inMemory.CouponRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class CouponService {

    private final CouponRepository couponRepo;
    private final CouponIssueRepository issueRepo;

    public CouponService(CouponRepository couponRepo, CouponIssueRepository issueRepo) {
        this.couponRepo = couponRepo;
        this.issueRepo = issueRepo;
    }

    public CouponIssue issueCoupon(Long couponId, Long userId) {
        Coupon coupon = couponRepo.findById(couponId)
                .orElseThrow(() -> new RuntimeException("No coupon"));
        if (coupon.getQuantity() <= 0) {
            throw new RuntimeException("No more coupon");
        }
        coupon.setQuantity(coupon.getQuantity() - 1);
        couponRepo.save(coupon);

        CouponIssue ci = new CouponIssue();
        ci.setCouponId(couponId);
        ci.setUserId(userId);
        ci.setActionType(CouponIssueType.UNUSED);
        issueRepo.save(ci);
        return ci;
    }

    public void useCoupon(Long couponIssueId, Long orderId, BigDecimal discount) {
        CouponIssue ci = issueRepo.findById(couponIssueId)
                .orElseThrow(() -> new RuntimeException("No issue"));
        ci.setActionType(CouponIssueType.USED);
        ci.setOrderId(orderId);
        ci.setAppliedDiscount(discount);
        ci.setAppliedTime(LocalDateTime.now());
        issueRepo.save(ci);
    }
}
