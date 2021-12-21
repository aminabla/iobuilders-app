package com.aminabla.wallet.application.ports.api.commands;

import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.domain.Amount;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class WithdrawMoneyCommand {


    private final WalletId sourceAccountId;

    private final WalletId targetAccountId;

    private final Amount amount;

    public WithdrawMoneyCommand(
            WalletId sourceAccountId,
            WalletId targetAccountId,
            Amount amo) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amo;
    }
}
