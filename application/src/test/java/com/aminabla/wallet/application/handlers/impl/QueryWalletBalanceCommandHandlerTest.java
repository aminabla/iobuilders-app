package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.QueryWalletBalanceCommand;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.ports.api.WalletInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class QueryWalletBalanceCommandHandlerTest {

    @Mock
    WalletInfo walletInfo;

    @InjectMocks
    QueryWalletBalanceCommandHandler queryWalletBalanceCommandHandler;


    @Test
    void handle() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        QueryWalletBalanceCommand command = new QueryWalletBalanceCommand(walletId);

        //when
        queryWalletBalanceCommandHandler.handle(command);

        //then
        Mockito.verify(walletInfo, times(1)).getBalance(walletId);
    }

    @Test
    void canHandle() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        QueryWalletBalanceCommand command = new QueryWalletBalanceCommand(walletId);

        //then
        assertThat(queryWalletBalanceCommandHandler.canHandle(command)).isTrue();
    }
}