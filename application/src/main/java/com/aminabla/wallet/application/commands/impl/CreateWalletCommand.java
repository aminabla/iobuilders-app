package com.aminabla.wallet.application.commands.impl;


import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.domain.Wallet;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
public class CreateWalletCommand implements Command {

    @NotNull
    Wallet.WalletId walletId;

    public CreateWalletCommand(Wallet.WalletId walletId) {
        this.walletId = walletId;
    }
}
