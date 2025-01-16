package kr.hhplus.be.server.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.domain.coupon.Coupon;
import kr.hhplus.be.server.domain.coupon.CouponIssue;
import kr.hhplus.be.server.domain.coupon.CouponService;
import kr.hhplus.be.server.interfaces.response.CouponIssueResponse;
import kr.hhplus.be.server.interfaces.response.CouponListResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Tag(name = "Coupon", description = "쿠폰 관련 API")
@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @Operation(summary = "선착순 쿠폰 발급", description = "선착순 쿠폰을 발급합니다.")
    @PostMapping("/{couponId}/issue")
    public CouponIssue issueCoupon(@PathVariable Long couponId,
                                   @RequestParam Long userId) {
        return couponService.issueCoupon(couponId, userId);
    }
    @Operation(summary = "보유 쿠폰 목록 조회", description = "사용자가 보유한 쿠폰 목록을 조회합니다.")
    @GetMapping
    public CouponIssue getCouponIssue(@PathVariable Long couponIssueId) {
        return couponService.getCouponIssue(couponIssueId);
    }

    @Operation(summary = "선착순 쿠폰 정보 조회", description = "선착순 쿠폰 정보를 조회합니다.")
    @GetMapping("/{couponId}")
    public Coupon getCoupon(@PathVariable Long couponId) {
        return couponService.getCoupon(couponId);
    }

}
