package com.aminabla.wallet.infra.persistence;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ops")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletOperationJpaEntity {

	@Id
	@GeneratedValue
	private Long id;

	@Column
	private LocalDateTime timestamp;

	@Column
	private Long sourceWalletId;

	@Column
	private Long targetWalletId;

	@Column
	private Double amount;

}
