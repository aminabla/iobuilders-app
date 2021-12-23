package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.application.commands.impl.TransferMoneyCommand;
import com.aminabla.wallet.application.event.EventId;
import com.aminabla.wallet.application.event.TransferEvent;
import com.aminabla.wallet.application.event.publisher.EventPublisher;
import com.aminabla.wallet.application.exception.CommandHandleException;
import com.aminabla.wallet.application.handlers.CommandHandler;
import com.aminabla.wallet.domain.ports.api.WalletOperations;

import java.util.UUID;

public class TransferMoneyCommandHandler implements CommandHandler<TransferMoneyCommand> {

    private final WalletOperations walletService;

    private final EventPublisher eventPublisher;

    public TransferMoneyCommandHandler(WalletOperations walletService, EventPublisher eventPublisher) {
        this.walletService = walletService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void handle(TransferMoneyCommand command) throws CommandHandleException {
        walletService.withdraw(command.getSourceWalletId(), command.getAmount());
        eventPublisher.publish(new TransferEvent(new EventId(UUID.randomUUID()), command.getTargetWalletId(), command.getAmount()));
    }

    @Override
    public <T extends Command> boolean canHandle(T command) {
        return TransferMoneyCommand.class.isAssignableFrom(command.getClass());
    }
}
