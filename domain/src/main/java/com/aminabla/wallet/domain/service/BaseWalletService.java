package com.aminabla.wallet.domain.service;

import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.domain.exception.WalletNotFoundException;
import com.aminabla.wallet.domain.ports.api.WalletInfo;
import com.aminabla.wallet.domain.ports.spi.LoadWalletPort;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static java.lang.String.format;

@Slf4j
public abstract class BaseWalletService {

	private final LoadWalletPort loadWalletPort;

	public BaseWalletService(LoadWalletPort loadAccountPort) {
		this.loadWalletPort = loadAccountPort;
	}

	protected Optional<Wallet> getWallet(Wallet.WalletId walletId) {
		log.debug("Retrieving wallet '{}' from port", walletId.walletAlias());
		return loadWalletPort.loadWallet(walletId);
	}

	protected void walletNotFound(Supplier<Wallet.WalletId> supplier){
		log.error("wallet '{}' not found", supplier.get().walletAlias());
		throw new WalletNotFoundException(format("Wallet: %s not found", supplier.get()));
	}

}
