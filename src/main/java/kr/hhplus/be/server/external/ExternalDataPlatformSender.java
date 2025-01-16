package kr.hhplus.be.server.external;
import kr.hhplus.be.server.domain.order.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ExternalDataPlatformSender {
    private final Logger log = LoggerFactory.getLogger(ExternalDataPlatformSender.class);

    public void sendOrderInfo(Order order) {
        log.info("[Mock] send order info: orderId={}", order.getOrderId());
    }
}
