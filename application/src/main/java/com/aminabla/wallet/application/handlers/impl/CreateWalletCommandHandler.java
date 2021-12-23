package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.CreateWalletCommand;
import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.application.handlers.CommandHandler;
import com.aminabla.wallet.domain.ports.api.WalletOperations;

public class CreateWalletCommandHandler implements CommandHandler<CreateWalletCommand> {

    private final WalletOperations walletService;

    public CreateWalletCommandHandler(WalletOperations walletService) {
        this.walletService = walletService;
    }

    @Override
    public void handle(CreateWalletCommand command) {
        walletService.create(command.getWalletId());
    }

    @Override
    public <T extends Command> boolean canHandle(T command) {
        return CreateWalletCommand.class.isAssignableFrom(command.getClass());
    }
}
