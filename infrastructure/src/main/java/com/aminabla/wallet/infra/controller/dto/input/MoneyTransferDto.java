package com.aminabla.wallet.infra.controller.dto.input;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;


@Data
public class MoneyTransferDto {
    @NotNull
    @Positive
    private Double amount;
}
