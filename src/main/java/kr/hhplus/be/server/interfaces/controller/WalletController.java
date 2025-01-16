package kr.hhplus.be.server.interfaces.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.hhplus.be.server.domain.user.UserService;
import kr.hhplus.be.server.interfaces.request.WalletRequest;
import kr.hhplus.be.server.interfaces.response.WalletResponse;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Tag(name = "Wallet", description = "잔액 관련 API")
@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    private final UserService userService;

    public WalletController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "잔액 조회", description = "사용자의 현재 잔액을 조회합니다.")
    @GetMapping("/{userId}/balance")
    public BigDecimal getBalance(@PathVariable Long userId) {
        return userService.getBalance(userId);
    }
    @Operation(summary = "잔액 충전", description = "사용자의 잔액을 충전합니다.")
    @PostMapping("/{userId}/charge")
    public BigDecimal chargeBalance(@PathVariable Long userId,
                                    @RequestParam BigDecimal amount) throws Exception {
        return userService.chargeBalance(userId, amount);
    }
}
