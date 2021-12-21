package com.aminabla.wallet.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface WalletOperationsRepository extends JpaRepository<WalletOperationJpaEntity, Long> {

	@Query("select a from WalletOperationJpaEntity a " +
			"where (a.targetWalletId = :walletId or a.sourceWalletId = :walletId) ")
	List<WalletOperationJpaEntity> findByWalletId(
			@Param("walletId") Long walletId);
}
