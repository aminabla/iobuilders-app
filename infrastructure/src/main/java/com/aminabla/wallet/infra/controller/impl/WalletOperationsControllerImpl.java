package com.aminabla.wallet.infra.controller.impl;

import com.aminabla.wallet.application.buses.CommandBus;
import com.aminabla.wallet.application.commands.impl.CreateWalletCommand;
import com.aminabla.wallet.application.commands.impl.DepositMoneyCommand;
import com.aminabla.wallet.application.commands.impl.TransferMoneyCommand;
import com.aminabla.wallet.application.commands.impl.WithdrawMoneyCommand;
import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.infra.controller.auth.AuthenticationAccessHelper;
import com.aminabla.wallet.infra.controller.WalletOperationsController;
import com.aminabla.wallet.infra.controller.dto.input.MoneyTransferDto;
import com.aminabla.wallet.infra.controller.dto.input.MoneyTransferToDto;
import com.aminabla.wallet.infra.controller.dto.input.WalletIdDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WalletOperationsControllerImpl implements WalletOperationsController {

    private final CommandBus commandBus;

    private final AuthenticationAccessHelper authenticationHelper;

    public WalletOperationsControllerImpl(CommandBus commandBus, AuthenticationAccessHelper authenticationHelper) {
        this.commandBus = commandBus;
        this.authenticationHelper = authenticationHelper;
    }


    @Override
    public void create(WalletIdDto user) {
        CreateWalletCommand command = new CreateWalletCommand(new WalletId(user.getWalletAlias(), authenticationHelper.getAuthentication().getName()));
        log.debug("Request for create wallet: {} for user '{}'", command.getWalletId().walletAlias(), command.getWalletId().ownerId());
        commandBus.handle(command);
    }


    @Override
    public void withdraw(String alias, MoneyTransferDto moneyTransferDto) {

        WithdrawMoneyCommand command = new WithdrawMoneyCommand(
                new WalletId(alias, authenticationHelper.getAuthentication().getName()),
                Money.of(moneyTransferDto.getAmount()));
        log.debug("Request for withdraw {}  on wallet: '{}'", command.getAmount().getAmount(), command.getWalletId().walletAlias());
        commandBus.handle(command);
    }

    @Override
    public void deposit(String alias, MoneyTransferDto moneyTransferDto) {

        DepositMoneyCommand command = new DepositMoneyCommand(
                new WalletId(alias, authenticationHelper.getAuthentication().getName()),
                Money.of(moneyTransferDto.getAmount()));
        log.debug("Request for deposit {}  on wallet: '{}'", command.getAmount().getAmount(), command.getWalletId().walletAlias());
        commandBus.handle(command);
    }

    @Override
    public void transfer(String alias, MoneyTransferToDto moneyTransferDto) {
        TransferMoneyCommand command = new TransferMoneyCommand(
                new WalletId(alias, authenticationHelper.getAuthentication().getName()),
                new WalletId(moneyTransferDto.getWalletAlias(), moneyTransferDto.getUserId()),
                Money.of(moneyTransferDto.getAmount())
        );
        log.debug("Request for transfer {}  from wallet: '{}' to wallet: '{}'", command.getAmount().getAmount(),
                command.getSourceWalletId().walletAlias(),
                command.getTargetWalletId().walletAlias());
        commandBus.handle(command);
    }

}
