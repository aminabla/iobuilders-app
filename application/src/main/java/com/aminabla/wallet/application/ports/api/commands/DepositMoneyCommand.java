package com.aminabla.wallet.application.ports.api.commands;

import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.Wallet.WalletId;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class DepositMoneyCommand {


    private final WalletId sourceAccountId;

    private final WalletId targetAccountId;

    private final Amount amount;

    public DepositMoneyCommand(
            WalletId sourceAccountId,
            WalletId targetAccountId,
            Amount amo) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amo;
    }
}
