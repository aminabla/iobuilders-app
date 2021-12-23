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

import static java.lang.String.format;


@Slf4j
public class WalletInfoService extends BaseWalletService implements WalletInfo {

	public WalletInfoService(LoadWalletPort loadWalletPort) {
		super(loadWalletPort);
	}

	@Override
	public Balance getBalance(Wallet.WalletId walletId) {
		log.debug("Retrieving wallet '{}' from port", walletId.walletAlias());
		return getWallet(walletId)
				.map(this::toBalance)
				.orElseThrow(() -> new WalletNotFoundException(format("Wallet: %s not found", walletId)));
	}

	@Override
	public List<WalletOperation> getOperationsHistory(Wallet.WalletId walletId) {
		log.debug("Retrieving operations history for wallet: '{}' from external port", walletId.walletAlias());
		return getWallet(walletId)
				.map(Wallet::history)
				.orElseThrow(() -> new WalletNotFoundException(format("Wallet: %s not found", walletId)));
	}

	private Balance toBalance(Wallet wallet){
		return new Balance(wallet.getId(), wallet.balance());
	}

}
