package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.DepositMoneyCommand;
import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.ports.api.WalletOperations;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DepositMoneyCommandHandlerTest {

    @Mock
    WalletOperations walletOperations;

    @InjectMocks
    DepositMoneyCommandHandler depositMoneyCommandHandler;

    @Test
    void handle() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        DepositMoneyCommand command = new DepositMoneyCommand(walletId, Money.of(100));

        //when
        depositMoneyCommandHandler.handle(command);

        //then
        verify(walletOperations, times(1)).deposit(walletId, Money.of(100));
    }

    @Test
    void canHandle() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        DepositMoneyCommand command = new DepositMoneyCommand(walletId, Money.of(100));

        //then
        assertThat(depositMoneyCommandHandler.canHandle(command)).isTrue();
    }
}