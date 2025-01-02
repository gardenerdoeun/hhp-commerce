package kr.hhplus.be.server.interfaces.controller;

import kr.hhplus.be.server.interfaces.request.WalletRequest;
import kr.hhplus.be.server.interfaces.response.WalletResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {


    @GetMapping("/{userId}")
    public WalletResponse getWallet(@PathVariable Long userId) {
        return new WalletResponse(userId, 10000.0);
    }

    @PostMapping("/charge")
    public WalletResponse charge(@RequestBody WalletRequest request) {
        double newBalance = 10000.0 + request.getAmount();
        return new WalletResponse(request.getUserId(), newBalance);
    }

}
