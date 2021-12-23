package com.aminabla.wallet.domain.ports.api;

import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet;

public interface WalletOperations {

	void withdraw(Wallet.WalletId walletId, Money amount);

	void deposit(Wallet.WalletId walletId, Money amount);

	void create(Wallet.WalletId walletId);

}
