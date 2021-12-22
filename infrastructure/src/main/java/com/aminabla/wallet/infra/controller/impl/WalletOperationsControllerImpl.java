package com.aminabla.wallet.infra.controller.impl;

import com.aminabla.wallet.application.buses.CommandBus;
import com.aminabla.wallet.application.commands.CreateWalletCommand;
import com.aminabla.wallet.application.commands.DepositMoneyCommand;
import com.aminabla.wallet.application.commands.WithdrawMoneyCommand;
import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.infra.controller.WalletOperationsController;
import com.aminabla.wallet.infra.controller.dto.input.MoneyTransferDto;
import com.aminabla.wallet.infra.controller.dto.input.WalletIdDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
public class WalletOperationsControllerImpl implements WalletOperationsController {

	private final CommandBus commandBus;

	public WalletOperationsControllerImpl(CommandBus commandBus) {
		this.commandBus = commandBus;
	}


	@Override
	public void create(@RequestBody @Valid WalletIdDto user, Principal principal) {
		CreateWalletCommand command = new CreateWalletCommand(new WalletId(user.getWalletAlias(), principal.getName()));
		commandBus.handle(command);
	}


	@Override
	public void withdraw(String walletAlias, @RequestBody MoneyTransferDto moneyTransferDto, Principal principal) {

		WithdrawMoneyCommand command = new WithdrawMoneyCommand(
				new WalletId(walletAlias, principal.getName()),
				Amount.of(moneyTransferDto.getAmount()));

		commandBus.handle(command);
	}

	@Override
	public void deposit(String walletAlias, @RequestBody MoneyTransferDto moneyTransferDto, Principal principal) {

		DepositMoneyCommand command = new DepositMoneyCommand(
				new WalletId(walletAlias, principal.getName()),
				Amount.of(moneyTransferDto.getAmount()));

		commandBus.handle(command);
	}

}
