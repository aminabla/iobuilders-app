package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.QueryWalletHistoryCommand;
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
class QueryWalletHistoryCommandHandlerTest {

    @Mock
    WalletInfo walletInfo;

    @InjectMocks
    QueryWalletHistoryCommandHandler queryWalletHistoryCommandHandler;


    @Test
    void handle() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        QueryWalletHistoryCommand command = new QueryWalletHistoryCommand(walletId);

        //when
        queryWalletHistoryCommandHandler.handle(command);

        //then
        Mockito.verify(walletInfo, times(1)).getOperationsHistory(walletId);
    }

    @Test
    void canHandle() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        QueryWalletHistoryCommand command = new QueryWalletHistoryCommand(walletId);

        //then
        assertThat(queryWalletHistoryCommandHandler.canHandle(command)).isTrue();
    }
}