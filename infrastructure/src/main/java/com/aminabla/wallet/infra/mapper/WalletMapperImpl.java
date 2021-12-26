package com.aminabla.wallet.infra.mapper;

import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.infra.persistence.entity.WalletEntityId;
import com.aminabla.wallet.infra.persistence.entity.WalletJpaEntity;
import com.aminabla.wallet.infra.persistence.entity.WalletOperationJpaEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class WalletMapperImpl implements WalletMapper {

	@Override
	public Wallet mapToDomainEntity(
			WalletJpaEntity wallet,
			List<WalletOperationJpaEntity> ops) {

		return new Wallet(
				new WalletId(wallet.getId().getAlias(), wallet.getId().getUserId()),
				mapToOperation(ops));

	}

	@Override
	public List<WalletOperation> mapToOperation(List<WalletOperationJpaEntity> operations) {
		List<WalletOperation> ops = new ArrayList<>();

		for (WalletOperationJpaEntity op : operations) {
			ops.add(new WalletOperation(
					new WalletOperation.WalletOperationId(op.getId()),
					new WalletId(op.getWalletId(), op.getOwnerId()),
					op.getTimestamp(),
					Money.of(op.getAmount())));
		}

		return ops;
	}

	@Override
	public WalletOperationJpaEntity mapToJpaEntity(WalletOperation operation) {
		return new WalletOperationJpaEntity(
				operation.getId() == null ? null : operation.getId().getIdentifier(),
				operation.getTimestamp(),
				operation.getWalletId().walletAlias(),
				operation.getWalletId().ownerId(),
				operation.getAmount().getAmount().doubleValue());
	}

	 @Override
	 public WalletJpaEntity mapToJpaEntity(Wallet wallet) {
		WalletJpaEntity entity = new WalletJpaEntity();
		if(Objects.nonNull(wallet.getId())){
			entity.setId(new WalletEntityId(wallet.getId().walletAlias(), wallet.getId().ownerId()));
		}

		return entity;
	}

}
