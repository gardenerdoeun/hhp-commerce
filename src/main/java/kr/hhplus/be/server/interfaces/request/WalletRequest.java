package kr.hhplus.be.server.interfaces.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class WalletRequest {
    private Long userId;
    private double amount;

    public Long getUserId() {
        return userId;
    }

    public double getAmount() {
        return amount;
    }
}
