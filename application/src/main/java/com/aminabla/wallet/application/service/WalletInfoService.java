package com.aminabla.wallet.application.service;

import com.aminabla.wallet.application.exception.WalletNotFoundException;
import com.aminabla.wallet.application.ports.api.WalletInfo;
import com.aminabla.wallet.application.ports.api.commands.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.ports.api.commands.QueryWalletHistoryCommand;
import com.aminabla.wallet.application.ports.spi.LoadWalletPort;
import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;

import java.util.List;
import java.util.Optional;

public class WalletInfoService implements WalletInfo {

	private final LoadWalletPort loadWalletPort;

	public WalletInfoService(LoadWalletPort loadAccountPort) {
		this.loadWalletPort = loadAccountPort;
	}

	@Override
	public Balance getBalance(QueryWalletBalanceCommand command) {
		return getWallet(command.getWalletId())
				.map(this::toBalance)
				.orElseThrow(() -> walletNotFound(command.getWalletId()));
	}

	@Override
	public List<WalletOperation> getOperationsHistory(QueryWalletHistoryCommand command) {
		return getWallet(command.getWalletId())
				.map(Wallet::history)
				.orElseThrow(() -> walletNotFound(command.getWalletId()));
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
