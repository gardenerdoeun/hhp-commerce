package kr.hhplus.be.server.domain.order;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {
    READY("주문 대기"),
    HOLD("주문 보류"),
    CANCELED("주문 취소"),
    COMPLETED("주문 완료");
    private final String message;
}
