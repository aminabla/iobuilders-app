package com.aminabla.wallet.infra.persistence;

import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.domain.ports.spi.LoadWalletPort;
import com.aminabla.wallet.domain.ports.spi.WalletStatePort;
import com.aminabla.wallet.infra.mapper.WalletMapper;
import com.aminabla.wallet.infra.persistence.entity.WalletEntityId;
import com.aminabla.wallet.infra.persistence.entity.WalletJpaEntity;
import com.aminabla.wallet.infra.persistence.entity.WalletOperationJpaEntity;
import com.aminabla.wallet.infra.persistence.repository.WalletOperationsRepository;
import com.aminabla.wallet.infra.persistence.repository.WalletRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WalletPersistenceAdapter implements LoadWalletPort, WalletStatePort {

	private final WalletRepository walletRepository;
	private final WalletOperationsRepository walletOpsRepository;
	private final WalletMapper walletMapper;

	public WalletPersistenceAdapter(WalletRepository walletRepository, WalletOperationsRepository walletOpsRepository, WalletMapper walletMapper) {
		this.walletRepository = walletRepository;
		this.walletOpsRepository = walletOpsRepository;
		this.walletMapper = walletMapper;
	}

	@Override
	public Optional<Wallet> loadWallet(Wallet.WalletId walletId) {

		return getWallet(walletId).map(walletJpaEntity -> {
			List<WalletOperationJpaEntity> ops =
					walletOpsRepository.findByWalletId(walletId.walletAlias(), walletId.ownerId());

			return walletMapper.mapToDomainEntity(
					walletJpaEntity,
					ops);
		});
	}


	@Override
	public void update(Wallet wallet) {
		for (WalletOperation operation : wallet.history()) {
			if (operation.getId() == null) {
				walletOpsRepository.save(walletMapper.mapToJpaEntity(operation));
			}
		}
	}

	@Override
	public Wallet create(Wallet.WalletId walletId) {
		return walletMapper.mapToDomainEntity(walletRepository.save(walletMapper.mapToJpaEntity(new Wallet(walletId))),
				Collections.emptyList());
	}

	private Optional<WalletJpaEntity> getWallet(Wallet.WalletId walletId){
		return walletRepository.findById(new WalletEntityId(walletId.walletAlias(), walletId.ownerId()));
	}

}
