package com.aminabla.wallet.domain.common;

import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.domain.Wallet;

import java.time.LocalDateTime;

public class WalletOperationTestData {

	public static class WalletOperationBuilder {
		private WalletOperation.WalletOperationId id;
		private Wallet.WalletId walletId;
		private LocalDateTime timestamp;
		private Money money;

		public WalletOperationBuilder() {
			this.timestamp = LocalDateTime.now();
		}

		public WalletOperationBuilder withId(WalletOperation.WalletOperationId id) {
			this.id = id;
			return this;
		}

		public WalletOperationBuilder withWalletId(Wallet.WalletId walletId) {
			this.walletId = walletId;
			return this;
		}

		public WalletOperationBuilder withTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public WalletOperationBuilder withMoney(Money money) {
			this.money = money;
			return this;
		}

		public WalletOperation build() {
			return new WalletOperation(
					this.id,
					this.walletId,
					this.timestamp,
					this.money);
		}
	}
}
