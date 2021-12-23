package com.aminabla.wallet.infra.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "wallet")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletJpaEntity {

	@EmbeddedId
	private WalletEntityId id;

	@Version
	private Long version;

}
