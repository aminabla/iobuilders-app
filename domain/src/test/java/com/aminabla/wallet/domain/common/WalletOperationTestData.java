package com.aminabla.wallet.domain.common;

import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.Wallet;

import java.time.LocalDateTime;

public class WalletOperationTestData {

	public static class WalletOperationBuilder {
		private WalletOperation.WalletOperationId id;
		private Wallet.WalletId sourceWalletId;
		private Wallet.WalletId targetWalletId;
		private LocalDateTime timestamp;
		private Amount money;

		public WalletOperationBuilder() {
			this.timestamp = LocalDateTime.now();
		}

		public WalletOperationBuilder withId(WalletOperation.WalletOperationId id) {
			this.id = id;
			return this;
		}

		public WalletOperationBuilder withSourceAccount(Wallet.WalletId walletId) {
			this.sourceWalletId = walletId;
			return this;
		}

		public WalletOperationBuilder withTargetAccount(Wallet.WalletId walletId) {
			this.targetWalletId = walletId;
			return this;
		}

		public WalletOperationBuilder withTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public WalletOperationBuilder withMoney(Amount money) {
			this.money = money;
			return this;
		}

		public WalletOperation build() {
			return new WalletOperation(
					this.id,
					this.sourceWalletId,
					this.targetWalletId,
					this.timestamp,
					this.money);
		}
	}
}
