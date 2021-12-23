package com.aminabla.wallet.infra.persistence.repository;

import com.aminabla.wallet.infra.persistence.entity.WalletEntityId;
import com.aminabla.wallet.infra.persistence.entity.WalletJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletJpaEntity, WalletEntityId> {
}
