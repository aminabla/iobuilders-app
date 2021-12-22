package com.aminabla.wallet.infra.controller;

import com.aminabla.wallet.infra.controller.dto.output.WalletBalanceDto;
import com.aminabla.wallet.infra.controller.dto.output.WalletOperationDto;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.List;

@RequestMapping("/wallets")
public interface WalletInfoController {

    @ApiOperation(value = "Get Wallet current balance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request if the wallet alias is absent in the request body or is not registered")
    })
    @GetMapping(value = "/{walletAlias}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    WalletBalanceDto getBalance(@ApiParam(name="alias", value = "wallet alias") @PathVariable("walletAlias") String walletAlias, @ApiIgnore Principal principal);

    @ApiOperation(value = "Get Wallet's history of operations over balance")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully"),
            @ApiResponse(code = 400, message = "Bad request if the wallet alias is absent in the request body or is not registered")
    })
    @GetMapping(value = "/{walletAlias}/history", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    List<WalletOperationDto> getHistory(@ApiParam(name="alias", value = "wallet alias") @PathVariable("walletAlias") String walletAlias, @ApiIgnore Principal principal);
}