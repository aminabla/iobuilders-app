package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.TransferMoneyCommand;
import com.aminabla.wallet.application.event.TransferEvent;
import com.aminabla.wallet.application.event.publisher.EventPublisher;
import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.exception.NotEnoughMoneyException;
import com.aminabla.wallet.domain.ports.api.WalletOperations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransferMoneyCommandHandlerTest {

    @Mock
    WalletOperations walletOperations;

    @Mock
    EventPublisher eventPublisher;

    @InjectMocks
    TransferMoneyCommandHandler transferMoneyCommandHandler;

    @Test
    void handle() {
        //given
        Wallet.WalletId sourceWalletId = new Wallet.WalletId("alias", "user");
        Wallet.WalletId targetWalletId = new Wallet.WalletId("alias02", "user02");
        Money amount = Money.of(100);
        TransferMoneyCommand command = new TransferMoneyCommand(sourceWalletId, targetWalletId, amount);

        //when
        transferMoneyCommandHandler.handle(command);

        //then
        verify(walletOperations, times(1)).withdraw(sourceWalletId, amount);
        verify(eventPublisher, times(1)).publish(any(TransferEvent.class));
    }

    @Test
    void handleNotEnoughMoney() {
        //given
        Wallet.WalletId sourceWalletId = new Wallet.WalletId("alias", "user");
        Wallet.WalletId targetWalletId = new Wallet.WalletId("alias02", "user02");
        Money amount = Money.of(100);
        TransferMoneyCommand command = new TransferMoneyCommand(sourceWalletId, targetWalletId, amount);
        doThrow(NotEnoughMoneyException.class).when(walletOperations).withdraw(sourceWalletId, amount);

        //when
        try{
            transferMoneyCommandHandler.handle(command);
        }catch (Exception ex){}

        //then
        verify(walletOperations, times(1)).withdraw(sourceWalletId, amount);
        verify(eventPublisher, never()).publish(any(TransferEvent.class));
    }

    @Test
    void canHandle() {
        Wallet.WalletId sourceWalletId = new Wallet.WalletId("alias", "user");
        Wallet.WalletId targetWalletId = new Wallet.WalletId("alias02", "user02");
        Money amount = Money.of(100);
        TransferMoneyCommand command = new TransferMoneyCommand(sourceWalletId, targetWalletId, amount);

        //then
        assertThat(transferMoneyCommandHandler.canHandle(command)).isTrue();
    }
}