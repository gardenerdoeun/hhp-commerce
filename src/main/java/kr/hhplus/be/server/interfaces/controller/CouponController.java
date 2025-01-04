package kr.hhplus.be.server.interfaces.controller;

import kr.hhplus.be.server.interfaces.response.CouponIssueResponse;
import kr.hhplus.be.server.interfaces.response.CouponListResponse;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @PostMapping("/{couponId}/issue")
    public CouponIssueResponse issueCoupon(@PathVariable Long couponId, @RequestParam Long userId) {
        return new CouponIssueResponse(userId,"coupon A","쿠폰 발급 완료");
    }

    @GetMapping
    public CouponListResponse getCoupons(@RequestParam Long userId) {
        if (userId == 123) {
            List<CouponListResponse.CouponDetail> couponDetailList = Arrays.asList(
                    new CouponListResponse.CouponDetail(1L, "coupon A", 10.0),
                    new CouponListResponse.CouponDetail(2L, "coupon A", 10.0)
            );
            return new CouponListResponse(userId, couponDetailList);
        }


        return null;
    }
}
