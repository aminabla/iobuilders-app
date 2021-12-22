package com.aminabla.wallet.infra.controller.dto.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class WalletIdDto {
    @ApiModelProperty(
            value = "alias for the wallet",
            name = "walletAlias",
            dataType = "String")
    @NotEmpty
    private String walletAlias;
}
