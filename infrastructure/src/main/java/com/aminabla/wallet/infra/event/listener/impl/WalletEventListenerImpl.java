package com.aminabla.wallet.infra.event.listener.impl;

import com.aminabla.wallet.application.buses.CommandBus;
import com.aminabla.wallet.application.commands.impl.DepositMoneyCommand;
import com.aminabla.wallet.application.event.TransferEvent;
import com.aminabla.wallet.infra.event.listener.WalletEventListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

@Slf4j
public class WalletEventListenerImpl implements WalletEventListener {

    private final CommandBus commandBus;

    public WalletEventListenerImpl(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @Async
    @EventListener
    @Override
    public void handleTransferEvent(TransferEvent event) {
        log.info("Transfer event received: {}", event);
        DepositMoneyCommand depositMoneyCommand = new DepositMoneyCommand(event.getWalletId(), event.getAmount());
        commandBus.handle(depositMoneyCommand);
    }

}
