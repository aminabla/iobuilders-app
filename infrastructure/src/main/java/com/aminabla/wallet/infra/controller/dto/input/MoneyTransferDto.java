package com.aminabla.wallet.infra.controller.dto.input;

import com.aminabla.wallet.domain.Wallet;
import lombok.Data;
import lombok.Value;


@Value
public class MoneyTransferDto {
    private Long sourceAccountId;
    private Long targetAccountId;
    private Double amount;
}
