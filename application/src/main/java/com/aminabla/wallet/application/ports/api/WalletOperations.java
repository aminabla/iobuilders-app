package com.aminabla.wallet.application.ports.api;

import com.aminabla.wallet.application.commands.CreateWalletCommand;
import com.aminabla.wallet.application.commands.DepositMoneyCommand;
import com.aminabla.wallet.application.commands.WithdrawMoneyCommand;
import com.aminabla.wallet.domain.Wallet;

public interface WalletOperations {

	void withdraw(WithdrawMoneyCommand amount);

	void deposit(DepositMoneyCommand amount);

	void create(CreateWalletCommand createAccountCommand);

}
