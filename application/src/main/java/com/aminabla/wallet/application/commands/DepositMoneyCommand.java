package com.aminabla.wallet.application.commands;

import com.aminabla.wallet.application.handlers.Command;
import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.User;
import com.aminabla.wallet.domain.Wallet.WalletId;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class DepositMoneyCommand implements Command {

    private final WalletId walletId;

    private final Amount amount;

    public DepositMoneyCommand(
            WalletId walletId,
            Amount amo) {
        this.walletId = walletId;
        this.amount = amo;
    }
}
