package com.aminabla.wallet.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<WalletJpaEntity, WalletEntityId> {
}
