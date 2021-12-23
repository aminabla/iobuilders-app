package com.aminabla.wallet.infra.controller;

import com.aminabla.wallet.infra.controller.dto.input.MoneyTransferDto;
import com.aminabla.wallet.infra.controller.dto.input.MoneyTransferToDto;
import com.aminabla.wallet.infra.controller.dto.input.WalletIdDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/wallets")
public interface WalletOperationsController {

    @Operation(summary = "Create Wallet", description = "", security = {
            @SecurityRequirement(name = "basicAuth")    }, tags={ "wallet-operations-controller" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully") })
    @PostMapping(
            consumes = { "application/json" })
    void create(@Parameter(in = ParameterIn.DEFAULT, description = "Identification (alias) for the wallet to be created", schema=@Schema()) @Valid @RequestBody WalletIdDto body);


    @Operation(summary = "Deposit money on Wallet", description = "", security = {
            @SecurityRequirement(name = "basicAuth")    }, tags={ "wallet-operations-controller" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully"),

            @ApiResponse(responseCode = "400", description = "Bad request if the wallet alias is absent in the request body or is not registered") })
    @PostMapping(value = "/{alias}/deposit",
            consumes = { "application/json" })
    void deposit(@Parameter(in = ParameterIn.PATH, description = "wallet alias", required=true, schema=@Schema()) @PathVariable("alias") String alias, @Parameter(in = ParameterIn.DEFAULT, description = "Amount to be transferred", schema=@Schema()) @Valid @RequestBody MoneyTransferDto body);




    @Operation(summary = "Transfer money from one Wallet to another", description = "", security = {
            @SecurityRequirement(name = "basicAuth")    }, tags={ "wallet-operations-controller" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully"),

            @ApiResponse(responseCode = "400", description = "Bad request if the wallet alias is absent in the request body or is not registered") })
    @PostMapping(value = "/{alias}/transfer",
            consumes = { "application/json" })
    void transfer(@Parameter(in = ParameterIn.PATH, description = "source wallet alias", required=true, schema=@Schema()) @PathVariable("alias") String alias, @Parameter(in = ParameterIn.DEFAULT, description = "Data of the wallet where the money is going to be transferred to", schema=@Schema()) @Valid @RequestBody MoneyTransferToDto body);


    @Operation(summary = "Withdraw money from  wallet", description = "", security = {
            @SecurityRequirement(name = "basicAuth")    }, tags={ "wallet-operations-controller" })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully"),

            @ApiResponse(responseCode = "400", description = "Bad request if the wallet alias is absent in the request body or is not registered") })
    @PostMapping(value = "/{alias}/withdraw",
            consumes = { "application/json" })
    void withdraw(@Parameter(in = ParameterIn.PATH, description = "wallet alias", required=true, schema=@Schema()) @PathVariable("alias") String alias, @Parameter(in = ParameterIn.DEFAULT, description = "Amount to be transferred", schema=@Schema()) @Valid @RequestBody MoneyTransferDto body);

}
