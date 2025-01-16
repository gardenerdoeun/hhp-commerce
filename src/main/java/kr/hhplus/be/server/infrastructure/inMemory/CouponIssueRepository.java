package kr.hhplus.be.server.infrastructure.inMemory;

import kr.hhplus.be.server.domain.coupon.CouponIssue;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CouponIssueRepository {
    private final Map<Long, CouponIssue> couponIssues = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public CouponIssue save(CouponIssue couponIssue) {
        if(couponIssue.getCouponIssueId() == null) {
            couponIssue.setCouponIssueId(idGenerator.incrementAndGet());
        }
        couponIssues.put(couponIssue.getCouponIssueId(), couponIssue);
        return couponIssue;
    }

    public Optional<CouponIssue> findById(long id) {
        return Optional.ofNullable(couponIssues.get(id));
    }
}
