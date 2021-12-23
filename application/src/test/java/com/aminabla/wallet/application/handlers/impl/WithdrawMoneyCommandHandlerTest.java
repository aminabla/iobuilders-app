package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.WithdrawMoneyCommand;
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
class WithdrawMoneyCommandHandlerTest {

    @Mock
    WalletOperations walletOperations;

    @InjectMocks
    WithdrawMoneyCommandHandler withdrawMoneyCommandHandler;

    @Test
    void handle() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        Money amount = Money.of(100);
        WithdrawMoneyCommand command = new WithdrawMoneyCommand(walletId, amount);

        //when
        withdrawMoneyCommandHandler.handle(command);

        //then
        verify(walletOperations, times(1)).withdraw(walletId, amount);
    }

    @Test
    void canHandle() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        WithdrawMoneyCommand command = new WithdrawMoneyCommand(walletId, Money.of(100));

        //then
        assertThat(withdrawMoneyCommandHandler.canHandle(command)).isTrue();
    }
}