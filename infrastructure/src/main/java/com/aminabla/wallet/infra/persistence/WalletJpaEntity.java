package com.aminabla.wallet.infra.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "wallet")
@Data
@AllArgsConstructor
@NoArgsConstructor
class WalletJpaEntity {

	@EmbeddedId
	private WalletEntityId id;

	@Version
	private Long version;

}
