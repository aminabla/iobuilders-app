package com.aminabla.wallet.application.ports.api;

import com.aminabla.wallet.application.ports.api.commands.CreateWalletCommand;
import com.aminabla.wallet.application.ports.api.commands.DepositMoneyCommand;
import com.aminabla.wallet.application.ports.api.commands.WithdrawMoneyCommand;
import com.aminabla.wallet.domain.Wallet;

public interface WalletOperations {

	void withdraw(WithdrawMoneyCommand amount);

	void deposit(DepositMoneyCommand amount);

	Wallet create(CreateWalletCommand createAccountCommand);

}
