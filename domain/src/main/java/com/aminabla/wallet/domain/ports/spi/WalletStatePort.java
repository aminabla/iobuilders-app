package com.aminabla.wallet.domain.ports.spi;


import com.aminabla.wallet.domain.Wallet;

public interface WalletStatePort {

	void update(Wallet wallet);

	Wallet create(Wallet.WalletId walletId);

}
