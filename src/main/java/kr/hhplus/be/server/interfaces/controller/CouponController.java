package kr.hhplus.be.server.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.interfaces.response.CouponIssueResponse;
import kr.hhplus.be.server.interfaces.response.CouponListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(name = "Coupon", description = "쿠폰 관련 API")
@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Operation(summary = "선착순 쿠폰 발급", description = "선착순 쿠폰을 발급합니다.")
    @PostMapping("/{couponId}/issue")
    public CouponIssueResponse issueCoupon(@PathVariable Long couponId, @RequestParam Long userId) {
        return new CouponIssueResponse(userId,"coupon A","쿠폰 발급 완료");
    }
    @Operation(summary = "보유 쿠폰 목록 조회", description = "사용자가 보유한 쿠폰 목록을 조회합니다.")
    @GetMapping
    public CouponListResponse getCoupons(@RequestParam Long userId) {
        if (userId == 123) {
            List<CouponListResponse.CouponDetail> couponDetailList = Arrays.asList(
                    new CouponListResponse.CouponDetail(1L, "coupon A", 10.0),
                    new CouponListResponse.CouponDetail(2L, "coupon B", 15.0)
            );
            return new CouponListResponse(userId, couponDetailList);
        }
        return null;
    }
}
