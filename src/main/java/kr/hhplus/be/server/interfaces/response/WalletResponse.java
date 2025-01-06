package kr.hhplus.be.server.interfaces.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class WalletResponse {
    private Long userId;
    private double balance;

    public WalletResponse(Long userId, double newBalance) {
        this.userId = userId;
        this.balance = newBalance;
    }
}
