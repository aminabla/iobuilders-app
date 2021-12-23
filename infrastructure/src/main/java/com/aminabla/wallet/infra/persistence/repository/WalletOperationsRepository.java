package com.aminabla.wallet.infra.persistence.repository;

import com.aminabla.wallet.infra.persistence.entity.WalletOperationJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WalletOperationsRepository extends JpaRepository<WalletOperationJpaEntity, Long> {

	@Query("select a from WalletOperationJpaEntity a " +
			"where a.walletId = :walletAlias AND a.ownerId = :ownerId ")
	List<WalletOperationJpaEntity> findByWalletId(
			@Param("walletAlias") String walletAlias, @Param("ownerId") String ownerId);
}
