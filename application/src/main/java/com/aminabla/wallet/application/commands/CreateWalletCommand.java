package com.aminabla.wallet.application.commands;


import com.aminabla.wallet.application.handlers.Command;
import com.aminabla.wallet.domain.User;
import com.aminabla.wallet.domain.Wallet;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode
public class CreateWalletCommand implements Command {


    private final Wallet.WalletId walletId;

    public CreateWalletCommand(Wallet.WalletId walletId) {
        this.walletId = walletId;
    }
}
