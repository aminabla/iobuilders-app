package com.aminabla.wallet.application.commands.impl;

import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet.WalletId;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class TransferMoneyCommand implements Command {

    @NotNull
    WalletId sourceWalletId;

    @NotNull
    WalletId targetWalletId;

    @NotNull
    Money amount;

    public TransferMoneyCommand(
            WalletId sourceWalletId,
            WalletId targetWalletId,
            Money amo) {
        this.sourceWalletId = sourceWalletId;
        this.targetWalletId = targetWalletId;
        this.amount = amo;
    }
}
