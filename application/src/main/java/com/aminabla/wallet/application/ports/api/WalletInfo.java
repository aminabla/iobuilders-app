package com.aminabla.wallet.application.ports.api;

import com.aminabla.wallet.application.ports.api.commands.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.ports.api.commands.QueryWalletHistoryCommand;
import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.WalletOperation;

import java.util.List;

public interface WalletInfo {
	Balance getBalance(QueryWalletBalanceCommand query);
	List<WalletOperation> getOperationsHistory(QueryWalletHistoryCommand query);
}
