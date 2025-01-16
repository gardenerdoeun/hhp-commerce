package kr.hhplus.be.server.infrastructure.inMemory;

import kr.hhplus.be.server.domain.coupon.Coupon;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CouponRepository {
    private final Map<Long, Coupon> coupons = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Coupon save(Coupon coupon) {
        if(coupon.getCouponId() == null) {
            coupon.setCouponId(idGenerator.incrementAndGet());
        }
        coupons.put(coupon.getCouponId(), coupon);
        return coupon;
    }

    public Optional<Coupon> findById(long couponId) {
        return Optional.ofNullable(coupons.get(couponId));
    }
}
