package kr.hhplus.be.server.domain.payment;

import kr.hhplus.be.server.domain.user.Wallet;
import kr.hhplus.be.server.exception.AppException;
import kr.hhplus.be.server.exception.ErrorCode;
import kr.hhplus.be.server.infrastructure.inMemory.PaymentRepository;
import kr.hhplus.be.server.infrastructure.inMemory.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepo;
    private final WalletRepository walletRepo;

    public PaymentService(PaymentRepository paymentRepo, WalletRepository walletRepo) {
        this.paymentRepo = paymentRepo;
        this.walletRepo = walletRepo;
    }

    public Payment pay(Long userId, Long orderId, BigDecimal amount) {
        // 잔액 차감
        Wallet wallet = walletRepo.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.WALLET_NOT_FOUND));
        if (wallet.getCurrentBalance().compareTo(amount) < 0) {
            throw new AppException(ErrorCode.INSUFFICIENT_BALANCE);
        }
        wallet.setCurrentBalance(wallet.getCurrentBalance().subtract(amount));
        walletRepo.save(wallet);

        // 결제 저장
        Payment p = new Payment();
        p.setOrderId(orderId);
        p.setUserId(userId);
        p.setPaymentAmount(amount);
        p.setPaymentStatus(PaymentStatus.PAID);
        paymentRepo.save(p);
        return p;
    }
}
