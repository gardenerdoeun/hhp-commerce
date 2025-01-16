package kr.hhplus.be.server.domain.product;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {
    SELLING("판매중"),
    HOLD("판매 보류"),
    STOP_SELLING("판매 중지");

    private final String text;
}
