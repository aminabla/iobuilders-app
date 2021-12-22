package com.aminabla.wallet.infra.controller.dto.input;

import com.aminabla.wallet.domain.Wallet;
import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
public class MoneyTransferDto {
    @NotNull
    @Positive
    private Double amount;
}
