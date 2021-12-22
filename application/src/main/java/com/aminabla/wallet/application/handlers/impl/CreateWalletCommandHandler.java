package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.CreateWalletCommand;
import com.aminabla.wallet.application.exception.CommandHandleException;
import com.aminabla.wallet.application.handlers.Command;
import com.aminabla.wallet.application.handlers.CommandHandler;
import com.aminabla.wallet.application.ports.api.WalletOperations;

public class CreateWalletCommandHandler implements CommandHandler<CreateWalletCommand> {

    private final WalletOperations walletService;

    public CreateWalletCommandHandler(WalletOperations walletService) {
        this.walletService = walletService;
    }

    @Override
    public void handle(CreateWalletCommand command) throws CommandHandleException {
        walletService.create(command);
    }

    @Override
    public <T extends Command> boolean canHandle(T command) {
        return CreateWalletCommand.class.isAssignableFrom(command.getClass());
    }
}
