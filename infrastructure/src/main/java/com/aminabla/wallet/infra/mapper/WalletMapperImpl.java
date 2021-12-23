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
			List<WalletOperationJpaEntity> activities) {

		return new Wallet(
				new WalletId(wallet.getId().getAlias(), wallet.getId().getUserId()),
				mapToActivityWindow(activities));

	}

	@Override
	public List<WalletOperation> mapToActivityWindow(List<WalletOperationJpaEntity> activities) {
		List<WalletOperation> mappedActivities = new ArrayList<>();

		for (WalletOperationJpaEntity activity : activities) {
			mappedActivities.add(new WalletOperation(
					new WalletOperation.WalletOperationId(activity.getId()),
					new WalletId(activity.getWalletId(), activity.getOwnerId()),
					activity.getTimestamp(),
					Money.of(activity.getAmount())));
		}

		return mappedActivities;
	}

	@Override
	public WalletOperationJpaEntity mapToJpaEntity(WalletOperation activity) {
		return new WalletOperationJpaEntity(
				activity.getId() == null ? null : activity.getId().getIdentifier(),
				activity.getTimestamp(),
				activity.getWalletId().walletAlias(),
				activity.getWalletId().ownerId(),
				activity.getAmount().getAmount().doubleValue());
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
