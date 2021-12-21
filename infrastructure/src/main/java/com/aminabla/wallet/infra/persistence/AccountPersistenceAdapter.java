package com.aminabla.wallet.infra.persistence;

import com.aminabla.wallet.application.ports.spi.LoadWalletPort;
import com.aminabla.wallet.application.ports.spi.WalletStatePort;
import com.aminabla.wallet.domain.User;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class AccountPersistenceAdapter implements LoadWalletPort, WalletStatePort {

	private final WalletRepository walletRepository;
	private final WalletOperationsRepository walletOpsRepository;
	private final WalletMapper walletMapper;

	public AccountPersistenceAdapter(WalletRepository walletRepository, WalletOperationsRepository walletOpsRepository, WalletMapper walletMapper) {
		this.walletRepository = walletRepository;
		this.walletOpsRepository = walletOpsRepository;
		this.walletMapper = walletMapper;
	}

	@Override
	public Optional<Wallet> loadWallet(Wallet.WalletId walletId) {

		return getWallet(walletId).map(walletJpaEntity -> {
			List<WalletOperationJpaEntity> activities =
					walletOpsRepository.findByWalletId(walletId.getIdentifier());

			return walletMapper.mapToDomainEntity(
					walletJpaEntity,
					activities);
		});
	}


	@Override
	public void update(Wallet wallet) {
		for (WalletOperation activity : wallet.history()) {
			if (activity.getId() == null) {
				walletOpsRepository.save(walletMapper.mapToJpaEntity(activity));
			}
		}
	}

	@Override
	public Wallet create(User user) {
		return walletMapper.mapToDomainEntity(walletRepository.save(walletMapper.mapToJpaEntity(new Wallet(user.getUserId()))),
				Collections.emptyList());
	}

	private Optional<WalletJpaEntity> getWallet(Wallet.WalletId walletId){
		return walletRepository.findById(walletId.getIdentifier());
	}

}
