package kr.hhplus.be.server.interfaces.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class WalletRequest {
    private Long userId;
    private double amount;
}
