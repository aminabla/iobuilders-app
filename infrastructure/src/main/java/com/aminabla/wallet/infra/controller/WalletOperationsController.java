package com.aminabla.wallet.infra.controller;


import com.aminabla.wallet.application.ports.api.WalletOperations;
import com.aminabla.wallet.application.ports.api.commands.CreateWalletCommand;
import com.aminabla.wallet.application.ports.api.commands.DepositMoneyCommand;
import com.aminabla.wallet.application.ports.api.commands.WithdrawMoneyCommand;
import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.User;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.infra.controller.dto.input.MoneyTransferDto;
import com.aminabla.wallet.infra.controller.dto.input.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
public class WalletOperationsController {

	private final WalletOperations walletOperations;

	public WalletOperationsController(WalletOperations walletOperations) {
		this.walletOperations = walletOperations;
	}


	@PostMapping
	public Long create(@RequestBody UserDto user) {
		CreateWalletCommand command = new CreateWalletCommand(new User(new User.UserId(user.getUserId())));
		return walletOperations.create(command).getId().getIdentifier();
	}


	@PostMapping("/withdraw")
	public void withdraw(@RequestBody MoneyTransferDto moneyTransferDto) {

		WithdrawMoneyCommand command = new WithdrawMoneyCommand(
				new WalletId(moneyTransferDto.getSourceAccountId()),
				new WalletId(moneyTransferDto.getTargetAccountId()),
				Amount.of(moneyTransferDto.getAmount()));

		walletOperations.withdraw(command);
	}

	@PostMapping("/deposit")
	public void deposit(@RequestBody  MoneyTransferDto moneyTransferDto) {

		DepositMoneyCommand command = new DepositMoneyCommand(
				new WalletId(moneyTransferDto.getSourceAccountId()),
				new WalletId(moneyTransferDto.getTargetAccountId()),
				Amount.of(moneyTransferDto.getAmount()));

		walletOperations.deposit(command);
	}

}
