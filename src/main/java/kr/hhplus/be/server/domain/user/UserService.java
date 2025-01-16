package kr.hhplus.be.server.domain.user;

import kr.hhplus.be.server.infrastructure.inMemory.UserRepository;
import kr.hhplus.be.server.infrastructure.inMemory.WalletRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    public UserService(UserRepository userRepository, WalletRepository walletRepository) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
    }

    public User createUser(String userName) {
        User user = new User(userName);
        userRepository.save(user);

        Wallet wallet = new Wallet();
        wallet.setUserId(user.getUserId());
        wallet.setCurrentBalance(BigDecimal.ZERO);
        walletRepository.save(wallet);
        return user;
    }

    public User getUser(Long userId) throws Exception {
        return userRepository.findById(userId)
                .orElseThrow(() -> new Exception("회원이 없습니다."));
    }

    public BigDecimal getBalance(Long userId) {
        return walletRepository.findByUserId(userId)
                .map(Wallet::getCurrentBalance)
                .orElse(BigDecimal.ZERO);
    }

    public BigDecimal chargeBalance(Long userId, BigDecimal amount) throws Exception {
        Wallet wallet = walletRepository.findByUserId(userId)
                .orElseThrow(() -> new Exception("지갑이 없습니다."));
        wallet.setCurrentBalance(wallet.getCurrentBalance().add(amount));
        walletRepository.save(wallet);
        return wallet.getCurrentBalance();
    }

}
