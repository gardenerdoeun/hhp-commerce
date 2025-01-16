package kr.hhplus.be.server.domain.user;

import kr.hhplus.be.server.infrastructure.inMemory.UserRepository;
import kr.hhplus.be.server.infrastructure.inMemory.WalletRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    @Test
    void testCreateUserAndCharge() throws Exception {
        UserRepository userRepository = new UserRepository();
        WalletRepository walletRepository = new WalletRepository();
        UserService userService = new UserService(userRepository, walletRepository);

        User user = userService.createUser("user1");
        assertNotNull(user.getUserId());

        BigDecimal newBal = userService.chargeBalance(user.getUserId(), BigDecimal.valueOf(1000));
        assertEquals(BigDecimal.valueOf(1000), newBal);

    }
}