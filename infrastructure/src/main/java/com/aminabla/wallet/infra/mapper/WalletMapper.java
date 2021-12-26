package com.aminabla.wallet.infra.mapper;

import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.infra.persistence.entity.WalletJpaEntity;
import com.aminabla.wallet.infra.persistence.entity.WalletOperationJpaEntity;

import java.util.List;

public interface WalletMapper {
    Wallet mapToDomainEntity(
            WalletJpaEntity wallet,
            List<WalletOperationJpaEntity> operations);

    List<WalletOperation> mapToOperation(List<WalletOperationJpaEntity> operations);

    WalletOperationJpaEntity mapToJpaEntity(WalletOperation operation);

    WalletJpaEntity mapToJpaEntity(Wallet wallet);
}
