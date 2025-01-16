package kr.hhplus.be.server.domain.payment;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {
    READY("결제 대기"),
    PAID("결제 완료"),
    CANCELED("결제 취소"),
    FAILED("결제 실패");
    private final String message;
}
