package kr.hhplus.be.server.domain.payment;

import static org.junit.jupiter.api.Assertions.*;

import kr.hhplus.be.server.domain.user.Wallet;
import kr.hhplus.be.server.infrastructure.inMemory.PaymentRepository;
import kr.hhplus.be.server.infrastructure.inMemory.WalletRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class PaymentServiceTest {

    @Test
    void testPay() {
        WalletRepository wrepo = new WalletRepository();
        PaymentRepository prepo = new PaymentRepository();
        PaymentService pservice = new PaymentService(prepo, wrepo);

        // 지갑 준비
        Wallet w = new Wallet();
        w.setUserId(1L);
        w.setCurrentBalance(BigDecimal.valueOf(5000));
        wrepo.save(w);

        // 결제
        Payment pay = pservice.pay(1L, 100L, BigDecimal.valueOf(3000));
        assertEquals(PaymentStatus.PAID, pay.getPaymentStatus());
        assertEquals(BigDecimal.valueOf(2000), w.getCurrentBalance());

        // 잔액 부족 예외
        assertThrows(RuntimeException.class, () ->
                pservice.pay(1L, 101L, BigDecimal.valueOf(3000)));
    }
}
