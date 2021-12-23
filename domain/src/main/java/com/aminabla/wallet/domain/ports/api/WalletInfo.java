package com.aminabla.wallet.domain.ports.api;

import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;

import java.util.List;

public interface WalletInfo {
	Balance getBalance(Wallet.WalletId query);
	List<WalletOperation> getOperationsHistory(Wallet.WalletId query);
}
