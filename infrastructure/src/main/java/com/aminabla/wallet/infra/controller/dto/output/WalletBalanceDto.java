package com.aminabla.wallet.infra.controller.dto.output;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WalletBalanceDto {
    private Long walletId;
    private BigDecimal amount;
}
