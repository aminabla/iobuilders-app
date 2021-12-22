package com.aminabla.wallet.application.service.common;

import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;

import java.time.LocalDateTime;

public class AccountOperationTestData {

	public static class AccountOperationBuilder {
		private WalletOperation.WalletOperationId id;
		private Wallet.WalletId walletId;
		private Wallet.WalletId targetWalletId;
		private LocalDateTime timestamp;
		private Amount money;

		public AccountOperationBuilder() {
			this.timestamp = LocalDateTime.now();
		}

		public AccountOperationBuilder withId(WalletOperation.WalletOperationId id) {
			this.id = id;
			return this;
		}

		public AccountOperationBuilder withWalletId(Wallet.WalletId walletId) {
			this.walletId = walletId;
			return this;
		}

		public AccountOperationBuilder withTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		public AccountOperationBuilder withMoney(Amount money) {
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
