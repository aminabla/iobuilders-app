package com.aminabla.wallet.application.commands;

import com.aminabla.wallet.application.handlers.Query;
import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.User;
import com.aminabla.wallet.domain.Wallet;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@EqualsAndHashCode
public class QueryWalletBalanceCommand implements Query<Balance> {
    private Wallet.WalletId walletId;
}
