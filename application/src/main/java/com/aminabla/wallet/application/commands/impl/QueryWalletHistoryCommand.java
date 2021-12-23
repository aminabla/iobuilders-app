package com.aminabla.wallet.application.commands.impl;

import com.aminabla.wallet.application.commands.Query;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;
import lombok.Value;

import javax.validation.constraints.NotNull;
import java.util.List;


@Value
public class QueryWalletHistoryCommand implements Query<List<WalletOperation>> {
    @NotNull
    Wallet.WalletId walletId;
}
