package com.aminabla.wallet.application.ports.spi;


import com.aminabla.wallet.domain.User;
import com.aminabla.wallet.domain.Wallet;

public interface WalletStatePort {

	void update(Wallet wallet);

	Wallet create(Wallet.WalletId walletId);

}
