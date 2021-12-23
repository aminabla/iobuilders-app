package com.aminabla.wallet.application.buses.impl;

import com.aminabla.wallet.application.buses.CommandBus;
import com.aminabla.wallet.application.buses.CommandValidator;
import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.application.commands.impl.CreateWalletCommand;
import com.aminabla.wallet.application.commands.impl.DepositMoneyCommand;
import com.aminabla.wallet.application.commands.impl.WithdrawMoneyCommand;
import com.aminabla.wallet.application.handlers.impl.CreateWalletCommandHandler;
import com.aminabla.wallet.application.handlers.impl.DepositMoneyCommandHandler;
import com.aminabla.wallet.application.handlers.impl.WithdrawMoneyCommandHandler;
import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.ports.api.WalletOperations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.List.of;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandBusImplTest {


    CommandBus commandBus;

    CreateWalletCommandHandler createWalletCommandHandler;

    DepositMoneyCommandHandler depositMoneyCommandHandler;

    WithdrawMoneyCommandHandler withdrawMoneyCommandHandler;

    WalletOperations walletOperations;


    CommandValidator<Command> commandValidator;


    @BeforeEach
    void setup() {
        commandValidator = mock(CommandValidator.class);

        walletOperations = mock(WalletOperations.class);

        createWalletCommandHandler = spy(new CreateWalletCommandHandler(walletOperations));

        depositMoneyCommandHandler = spy(new DepositMoneyCommandHandler(walletOperations));

        withdrawMoneyCommandHandler = spy(new WithdrawMoneyCommandHandler(walletOperations));

        this.commandBus = new CommandBusImpl(List.of(createWalletCommandHandler, depositMoneyCommandHandler, withdrawMoneyCommandHandler), commandValidator);
    }


    @Test
    void handleDepositMoneyCommand() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        DepositMoneyCommand depositMoneyCommand = new DepositMoneyCommand(walletId, Money.of(10));
        doNothing().when(walletOperations).deposit(any(Wallet.WalletId.class), any(Money.class));

        //when
        commandBus.handle(depositMoneyCommand);

        //then
        Mockito.verify(depositMoneyCommandHandler, times(1)).handle(depositMoneyCommand);
    }

    @Test
    void handleWithdrawMoneyCommand() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        WithdrawMoneyCommand withdrawMoneyCommand = new WithdrawMoneyCommand(walletId, Money.of(10));
        doNothing().when(walletOperations).withdraw(any(Wallet.WalletId.class), any(Money.class));

        //when
        commandBus.handle(withdrawMoneyCommand);

        //then
        Mockito.verify(withdrawMoneyCommandHandler, times(1)).handle(withdrawMoneyCommand);
    }

    @Test
    void handleCreateWalletCommand() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        CreateWalletCommand createWalletCommand = new CreateWalletCommand(walletId);
        doNothing().when(walletOperations).create(any(Wallet.WalletId.class));

        //when
        commandBus.handle(createWalletCommand);

        //then
        Mockito.verify(createWalletCommandHandler, times(1)).handle(createWalletCommand);
    }
}