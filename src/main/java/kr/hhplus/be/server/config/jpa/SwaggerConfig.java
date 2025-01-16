package kr.hhplus.be.server.config.jpa;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("E-Commerce API").description("""
                              - 상품 목록 조회 API
                              - 인기 판매 상품 조회 API
                              - 쿠폰 발급 API
                              - 보유 쿠폰 목록 조회 API
                              - 잔액 충전/조회 API
                              - 주문/결제 API
                """).version("v1"));
    }
}
