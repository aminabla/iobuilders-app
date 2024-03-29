package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.WithdrawMoneyCommand;
import com.aminabla.wallet.application.exception.CommandHandleException;
import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.application.handlers.CommandHandler;
import com.aminabla.wallet.domain.ports.api.WalletOperations;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WithdrawMoneyCommandHandler implements CommandHandler<WithdrawMoneyCommand> {

    private final WalletOperations walletService;

    public WithdrawMoneyCommandHandler(WalletOperations walletService) {
        this.walletService = walletService;
    }

    @Override
    public void handle(WithdrawMoneyCommand command) throws CommandHandleException {
        log.debug("Withdraw {} from wallet: '{}'", command.getAmount().getAmount(), command.getWalletId().walletAlias());
        walletService.withdraw(command.getWalletId(), command.getAmount());
    }

    @Override
    public <T extends Command> boolean canHandle(T command) {
        return WithdrawMoneyCommand.class.isAssignableFrom(command.getClass());
    }
}
