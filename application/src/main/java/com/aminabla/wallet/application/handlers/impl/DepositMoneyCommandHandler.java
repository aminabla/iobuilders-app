package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.CreateWalletCommand;
import com.aminabla.wallet.application.commands.DepositMoneyCommand;
import com.aminabla.wallet.application.exception.CommandHandleException;
import com.aminabla.wallet.application.handlers.Command;
import com.aminabla.wallet.application.handlers.CommandHandler;
import com.aminabla.wallet.application.ports.api.WalletOperations;
import com.aminabla.wallet.application.service.WalletService;

public class DepositMoneyCommandHandler implements CommandHandler<DepositMoneyCommand> {

    private final WalletOperations walletService;

    public DepositMoneyCommandHandler(WalletOperations walletService) {
        this.walletService = walletService;
    }

    @Override
    public void handle(DepositMoneyCommand command) throws CommandHandleException {
        walletService.deposit(command);
    }

    @Override
    public <T extends Command> boolean canHandle(T command) {
        return DepositMoneyCommand.class.isAssignableFrom(command.getClass());
    }
}
