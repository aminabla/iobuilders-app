package com.aminabla.wallet.application.commands;

import com.aminabla.wallet.application.handlers.Command;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.domain.Amount;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class TransferMoneyCommand implements Command {


    private final WalletId sourceAccountId;

    private final WalletId targetAccountId;

    private final Amount amount;

    public TransferMoneyCommand(
            WalletId sourceAccountId,
            WalletId targetAccountId,
            Amount amo) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amo;
    }
}
