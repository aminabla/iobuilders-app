package com.aminabla.wallet.domain.service;

import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.domain.exception.WalletNotFoundException;
import com.aminabla.wallet.domain.ports.api.WalletInfo;
import com.aminabla.wallet.domain.ports.spi.LoadWalletPort;

import java.util.List;
import java.util.Optional;

public class WalletInfoService implements WalletInfo {

	private final LoadWalletPort loadWalletPort;

	public WalletInfoService(LoadWalletPort loadAccountPort) {
		this.loadWalletPort = loadAccountPort;
	}

	@Override
	public Balance getBalance(Wallet.WalletId walletId) {
		return getWallet(walletId)
				.map(this::toBalance)
				.orElseThrow(() -> walletNotFound(walletId));
	}

	@Override
	public List<WalletOperation> getOperationsHistory(Wallet.WalletId walletId) {
		return getWallet(walletId)
				.map(Wallet::history)
				.orElseThrow(() -> walletNotFound(walletId));
	}

	private Balance toBalance(Wallet wallet){
		return new Balance(wallet.getId(), wallet.balance());
	}

	private Optional<Wallet> getWallet(Wallet.WalletId walletId) {
		return loadWalletPort.loadWallet(walletId);
	}

	private WalletNotFoundException walletNotFound(Wallet.WalletId walletId){
		return new WalletNotFoundException(String.format("Wallet: %s not found", walletId));
	}
}
