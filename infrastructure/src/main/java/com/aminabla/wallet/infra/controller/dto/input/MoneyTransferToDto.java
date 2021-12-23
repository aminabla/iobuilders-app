package com.aminabla.wallet.infra.controller.dto.input;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class MoneyTransferToDto extends MoneyTransferDto{

    @NotEmpty
    private String userId;

    @NotEmpty
    private String walletAlias;
}
