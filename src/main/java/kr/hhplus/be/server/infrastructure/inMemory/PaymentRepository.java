package kr.hhplus.be.server.infrastructure.inMemory;

import kr.hhplus.be.server.domain.payment.Payment;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PaymentRepository {
    private final Map<Long, Payment> payments = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong();

    public Payment save(Payment payment) {
        if(payment.getPaymentId() == null) {
            payment.setPaymentId(idGenerator.incrementAndGet());
        }
        payments.put(payment.getPaymentId(), payment);
        return payment;
    }

    public Optional<Payment> findById(long paymentId) {
        return Optional.ofNullable(payments.get(paymentId));
    }
}
