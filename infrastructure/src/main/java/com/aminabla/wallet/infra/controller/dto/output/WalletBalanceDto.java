package com.aminabla.wallet.infra.controller.dto.output;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletBalanceDto {
    private String walletId;
    private BigDecimal amount;
}
