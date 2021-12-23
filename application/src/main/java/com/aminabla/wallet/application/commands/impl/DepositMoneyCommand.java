package com.aminabla.wallet.application.commands.impl;

import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet.WalletId;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class DepositMoneyCommand implements Command {

    @NotNull
    WalletId walletId;

    @NotNull
    Money amount;

    public DepositMoneyCommand(
            WalletId walletId,
            Money amo) {
        this.walletId = walletId;
        this.amount = amo;
    }
}
