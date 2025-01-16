package kr.hhplus.be.server.infrastructure.inMemory;

import kr.hhplus.be.server.domain.user.Wallet;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class WalletRepository {
    private final Map<Long, Wallet> store = new ConcurrentHashMap<Long, Wallet>();
    private static AtomicLong idGenerator = new AtomicLong(0);

    public Wallet save(Wallet wallet) {
        if(wallet.getWalletId() == null){
            wallet.setWalletId(idGenerator.incrementAndGet());
        }
        store.put(wallet.getWalletId(), wallet);
        return wallet;
    }

    public Optional<Wallet> findByUserId(Long userId) {
        return store.values().stream().filter(w -> w.getWalletId().equals(userId)).findFirst();
    }
}
