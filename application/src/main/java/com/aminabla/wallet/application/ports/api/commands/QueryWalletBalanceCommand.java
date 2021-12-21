package com.aminabla.wallet.application.ports.api.commands;

import com.aminabla.wallet.domain.Wallet;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@EqualsAndHashCode
public class QueryWalletBalanceCommand {
    private Wallet.WalletId walletId;
}
