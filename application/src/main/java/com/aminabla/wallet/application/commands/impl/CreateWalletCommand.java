package com.aminabla.wallet.application.commands.impl;


import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.domain.Wallet;
import lombok.Value;

@Value
public class CreateWalletCommand implements Command {


    private final Wallet.WalletId walletId;

    public CreateWalletCommand(Wallet.WalletId walletId) {
        this.walletId = walletId;
    }
}
