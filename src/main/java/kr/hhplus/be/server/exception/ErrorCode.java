package kr.hhplus.be.server.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없음"),
    WALLET_NOT_FOUND(HttpStatus.NOT_FOUND, "지갑을 찾을 수 없음"),
    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, "잔액 부족"),
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "상품이 존재하지 않음"),
    STOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "재고 정보 없음"),
    OUT_OF_STOCK(HttpStatus.BAD_REQUEST, "재고가 부족"),
    COUPON_NOT_FOUND(HttpStatus.NOT_FOUND, "쿠폰이 존재하지 않음"),
    COUPON_EXHAUSTED(HttpStatus.BAD_REQUEST, "쿠폰이 소진됨"),
    COUPON_ISSUE_NOT_FOUND(HttpStatus.NOT_FOUND, "쿠폰 발급 이력 없음"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러");

    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
}

