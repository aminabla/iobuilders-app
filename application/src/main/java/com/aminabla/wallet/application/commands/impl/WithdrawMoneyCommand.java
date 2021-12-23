package com.aminabla.wallet.application.commands.impl;

import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet.WalletId;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class WithdrawMoneyCommand implements Command {

    @NotNull
    WalletId walletId;

    @NotNull
    Money amount;

    public WithdrawMoneyCommand(
            WalletId walletId,
            Money amo) {
        this.walletId = walletId;
        this.amount = amo;
    }
}
