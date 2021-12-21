package com.aminabla.wallet.application.ports.spi;

import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.Wallet.WalletId;

import java.util.Optional;

public interface LoadWalletPort {
	Optional<Wallet> loadWallet(WalletId walletId);
}
