package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.application.commands.impl.TransferMoneyCommand;
import com.aminabla.wallet.application.event.EventId;
import com.aminabla.wallet.application.event.TransferEvent;
import com.aminabla.wallet.application.event.publisher.EventPublisher;
import com.aminabla.wallet.application.exception.CommandHandleException;
import com.aminabla.wallet.application.handlers.CommandHandler;
import com.aminabla.wallet.domain.ports.api.WalletOperations;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class TransferMoneyCommandHandler implements CommandHandler<TransferMoneyCommand> {

    private final WalletOperations walletService;

    private final EventPublisher eventPublisher;

    public TransferMoneyCommandHandler(WalletOperations walletService, EventPublisher eventPublisher) {
        this.walletService = walletService;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public void handle(TransferMoneyCommand command) throws CommandHandleException {
        log.debug("Transfer {} from wallet: '{}' to wallet: '{}'", command.getAmount().getAmount(), command.getSourceWalletId().walletAlias(), command.getTargetWalletId().walletAlias());
        walletService.withdraw(command.getSourceWalletId(), command.getAmount());
        log.debug("Withdraw completed. Communicating to continue with the transfer", command.getAmount().getAmount(), command.getSourceWalletId().walletAlias(), command.getTargetWalletId().walletAlias());
        eventPublisher.publish(new TransferEvent(new EventId(UUID.randomUUID()), command.getTargetWalletId(), command.getAmount()));
    }

    @Override
    public <T extends Command> boolean canHandle(T command) {
        return TransferMoneyCommand.class.isAssignableFrom(command.getClass());
    }
}
