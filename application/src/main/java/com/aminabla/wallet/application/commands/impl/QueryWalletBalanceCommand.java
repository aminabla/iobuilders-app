package com.aminabla.wallet.application.commands.impl;

import com.aminabla.wallet.application.commands.Query;
import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.Wallet;
import lombok.Value;

import javax.validation.constraints.NotNull;


@Value
public class QueryWalletBalanceCommand implements Query<Balance> {
    @NotNull
    Wallet.WalletId walletId;
}
