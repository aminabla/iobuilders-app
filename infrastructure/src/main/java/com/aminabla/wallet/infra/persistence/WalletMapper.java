package com.aminabla.wallet.infra.persistence;

import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.User;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.domain.WalletOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class WalletMapper {

	Wallet mapToDomainEntity(
			WalletJpaEntity wallet,
			List<WalletOperationJpaEntity> activities) {

		return new Wallet(
				new WalletId(wallet.getId()),
				new User.UserId(wallet.getUserId()),
				mapToActivityWindow(activities));

	}

	List<WalletOperation> mapToActivityWindow(List<WalletOperationJpaEntity> activities) {
		List<WalletOperation> mappedActivities = new ArrayList<>();

		for (WalletOperationJpaEntity activity : activities) {
			mappedActivities.add(new WalletOperation(
					new WalletOperation.WalletOperationId(activity.getId()),
					new WalletId(activity.getSourceWalletId()),
					new WalletId(activity.getTargetWalletId()),
					activity.getTimestamp(),
					Amount.of(activity.getAmount())));
		}

		return mappedActivities;
	}

	WalletOperationJpaEntity mapToJpaEntity(WalletOperation activity) {
		return new WalletOperationJpaEntity(
				activity.getId() == null ? null : activity.getId().getIdentifier(),
				activity.getTimestamp(),
				activity.getSourceWalletId().getIdentifier(),
				activity.getTargetWalletId().getIdentifier(),
				activity.getAmount().getAmount().doubleValue());
	}

	 WalletJpaEntity mapToJpaEntity(Wallet wallet) {
		WalletJpaEntity entity = new WalletJpaEntity();
		if(Objects.nonNull(wallet.getId())){
			entity.setId(wallet.getId().getIdentifier());
		}
		entity.setUserId(wallet.getUserId().getIdentifier());

		return entity;
	}

}
