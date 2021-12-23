package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.DepositMoneyCommand;
import com.aminabla.wallet.application.exception.CommandHandleException;
import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.application.handlers.CommandHandler;
import com.aminabla.wallet.domain.ports.api.WalletOperations;

public class DepositMoneyCommandHandler implements CommandHandler<DepositMoneyCommand> {

    private final WalletOperations walletService;

    public DepositMoneyCommandHandler(WalletOperations walletService) {
        this.walletService = walletService;
    }

    @Override
    public void handle(DepositMoneyCommand command) throws CommandHandleException {
        walletService.deposit(command.getWalletId(), command.getAmount());
    }

    @Override
    public <T extends Command> boolean canHandle(T command) {
        return DepositMoneyCommand.class.isAssignableFrom(command.getClass());
    }
}
