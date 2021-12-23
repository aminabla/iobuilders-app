package com.aminabla.wallet.infra.controller;

import com.aminabla.wallet.infra.controller.dto.output.WalletBalanceDto;
import com.aminabla.wallet.infra.controller.dto.output.WalletOperationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/wallets")
public interface WalletInfoController {

    @Operation(summary = "Get Wallet current balance", description = "", security = {
            @SecurityRequirement(name = "basicAuth")}, tags = {"wallet-info-controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = WalletBalanceDto.class))),

            @ApiResponse(responseCode = "400", description = "Bad request if the wallet alias is absent in the request body or is not registered")})
    @GetMapping(value = "/{alias}",
            produces = {"application/json"})
    WalletBalanceDto getBalance(@Parameter(in = ParameterIn.PATH, description = "wallet alias", required = true, schema = @Schema()) @PathVariable("alias") String alias);


    @Operation(summary = "Get Wallet's history of operations over balance", description = "", security = {
            @SecurityRequirement(name = "basicAuth")}, tags = {"wallet-info-controller"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully", content = @Content(mediaType = "*/*", array = @ArraySchema(schema = @Schema(implementation = WalletOperationDto.class)))),

            @ApiResponse(responseCode = "400", description = "Bad request if the wallet alias is absent in the request body or is not registered")})
    @GetMapping(value = "/{alias}/history",
            produces = {"application/json"})
    List<WalletOperationDto> getHistory(@Parameter(in = ParameterIn.PATH, description = "wallet alias", required = true, schema = @Schema()) @PathVariable("alias") String alias);

}