package com.aminabla.wallet.application.commands;

import com.aminabla.wallet.application.handlers.Query;
import com.aminabla.wallet.domain.User;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;


@Value
@EqualsAndHashCode
public class QueryWalletHistoryCommand implements Query<List<WalletOperation>> {
    private Wallet.WalletId walletId;
}
