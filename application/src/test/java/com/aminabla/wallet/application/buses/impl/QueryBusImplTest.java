package com.aminabla.wallet.application.buses.impl;

import com.aminabla.wallet.application.buses.CommandValidator;
import com.aminabla.wallet.application.buses.QueryBus;
import com.aminabla.wallet.application.commands.Query;
import com.aminabla.wallet.application.commands.impl.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.commands.impl.QueryWalletHistoryCommand;
import com.aminabla.wallet.application.handlers.impl.*;
import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.ports.api.WalletInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QueryBusImplTest {


    QueryBus queryBus;

    QueryWalletBalanceCommandHandler queryWalletBalanceCommandHandler;

    QueryWalletHistoryCommandHandler queryWalletHistoryCommandHandler;

    WalletInfo walletInfo;

    CommandValidator<Query> commandValidator;


    @BeforeEach
    void setup() {
        commandValidator = mock(CommandValidator.class);

        walletInfo = mock(WalletInfo.class);

        queryWalletBalanceCommandHandler = spy(new QueryWalletBalanceCommandHandler(walletInfo));

        queryWalletHistoryCommandHandler = spy(new QueryWalletHistoryCommandHandler(walletInfo));

        this.queryBus = new QueryBusImpl(List.of(queryWalletBalanceCommandHandler, queryWalletHistoryCommandHandler), commandValidator);
    }


    @Test
    void handleQueryBalanceCommand() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        Balance balance = new Balance(walletId, Money.of(1000));
        QueryWalletBalanceCommand queryWalletBalanceCommand = new QueryWalletBalanceCommand(walletId);
        when(walletInfo.getBalance(any(Wallet.WalletId.class))).thenReturn(balance);

        //when
        queryBus.handle(queryWalletBalanceCommand);

        //then
        Mockito.verify(queryWalletBalanceCommandHandler, times(1)).handle(queryWalletBalanceCommand);
    }

    @Test
    void handleQueryHistoryCommand() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        QueryWalletHistoryCommand queryWalletHistoryCommand = new QueryWalletHistoryCommand(walletId);
        when(walletInfo.getOperationsHistory(any(Wallet.WalletId.class))).thenReturn(Collections.emptyList());

        //when
        queryBus.handle(queryWalletHistoryCommand);

        //then
        Mockito.verify(queryWalletHistoryCommandHandler, times(1)).handle(queryWalletHistoryCommand);
    }
}