package com.aminabla.wallet.application.ports.api.commands;


import com.aminabla.wallet.domain.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Value;

@Value
@EqualsAndHashCode
public class CreateWalletCommand {

    private final User user;

    public CreateWalletCommand(User user) {
        this.user = user;
    }
}
