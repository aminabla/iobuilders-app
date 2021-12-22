package com.aminabla.wallet.infra.controller;

import com.aminabla.wallet.infra.controller.dto.input.MoneyTransferDto;
import com.aminabla.wallet.infra.controller.dto.input.WalletIdDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;

@RequestMapping("/wallets")
public interface WalletOperationsController {
    @ApiOperation(value = "Create Wallet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully")
    })
    @PostMapping
    void create(@ApiParam(name="walletId", value = "Identification (alias) for the wallet to be created")
                @RequestBody WalletIdDto walletId, @ApiIgnore Principal principal);

    @ApiOperation(value = "Withdraw money from  wallet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully")
    })
    @PostMapping("/{walletAlias}/withdraw")
    void withdraw(@ApiParam(name="alias", value = "wallet alias") @PathVariable("walletAlias") String walletAlias,
                  @ApiParam(name="moneytransfer", value = "Amount to be transferred")@RequestBody MoneyTransferDto moneyTransferDto,
                  @ApiIgnore Principal principal);

    @ApiOperation(value = "Deposit money on Wallet")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully")
    })
    @PostMapping("/{walletAlias}/deposit")
    void deposit(@ApiParam(name="alias", value = "wallet alias") @PathVariable("walletAlias") String walletAlias,
                 @ApiParam(name="moneytransfer", value = "Amount to be transferred") @Validated @RequestBody MoneyTransferDto moneyTransferDto,
                 @ApiIgnore Principal principal);
}
