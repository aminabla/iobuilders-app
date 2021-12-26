package com.aminabla.wallet.application.buses.impl;

import com.aminabla.wallet.application.buses.CommandBus;
import com.aminabla.wallet.application.buses.CommandValidator;
import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.application.commands.impl.CreateWalletCommand;
import com.aminabla.wallet.application.commands.impl.DepositMoneyCommand;
import com.aminabla.wallet.application.commands.impl.TransferMoneyCommand;
import com.aminabla.wallet.application.commands.impl.WithdrawMoneyCommand;
import com.aminabla.wallet.application.event.publisher.EventPublisher;
import com.aminabla.wallet.application.exception.CommandInvalidException;
import com.aminabla.wallet.application.handlers.impl.CreateWalletCommandHandler;
import com.aminabla.wallet.application.handlers.impl.DepositMoneyCommandHandler;
import com.aminabla.wallet.application.handlers.impl.TransferMoneyCommandHandler;
import com.aminabla.wallet.application.handlers.impl.WithdrawMoneyCommandHandler;
import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.ports.api.WalletOperations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.List.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommandBusImplTest {


    CommandBus commandBus;

    CreateWalletCommandHandler createWalletCommandHandler;

    DepositMoneyCommandHandler depositMoneyCommandHandler;

    WithdrawMoneyCommandHandler withdrawMoneyCommandHandler;

    TransferMoneyCommandHandler transferMoneyCommandHandler;

    WalletOperations walletOperations;

    EventPublisher eventPublisher;

    @Spy
    CommandValidator<Command> commandValidator;


    @BeforeEach
    void setup() {
        commandValidator = new ValidatorImpl<>();

        eventPublisher = mock(EventPublisher.class);

        walletOperations = mock(WalletOperations.class);

        createWalletCommandHandler = spy(new CreateWalletCommandHandler(walletOperations));

        depositMoneyCommandHandler = spy(new DepositMoneyCommandHandler(walletOperations));

        withdrawMoneyCommandHandler = spy(new WithdrawMoneyCommandHandler(walletOperations));

        transferMoneyCommandHandler = spy(new TransferMoneyCommandHandler(walletOperations, eventPublisher));

        this.commandBus = new CommandBusImpl(List.of(createWalletCommandHandler,
                depositMoneyCommandHandler,
                withdrawMoneyCommandHandler,
                transferMoneyCommandHandler
        ), commandValidator);
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
    void handleTransferMoneyCommand() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        Wallet.WalletId targetWalletId = new Wallet.WalletId("alias02", "user02");
        TransferMoneyCommand command = new TransferMoneyCommand(walletId, targetWalletId, Money.of(10));
        doNothing().when(walletOperations).withdraw(any(Wallet.WalletId.class), any(Money.class));

        //when
        commandBus.handle(command);

        //then
        Mockito.verify(transferMoneyCommandHandler, times(1)).handle(command);
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


    @Test
    void createWalletCommandInvalid() {
        //given
        CreateWalletCommand command = new CreateWalletCommand(null);

        //when
        CommandInvalidException result = Assertions.assertThrows(CommandInvalidException.class, () -> commandBus.handle(command));

        //then
        assertThat(result.getConstraintViolations()).isNotEmpty();
        verify(walletOperations, never()).create(null);
        verify(walletOperations, never()).create(any(Wallet.WalletId.class));
    }

    @Test
    void depositMoneyCommandInvalid() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        DepositMoneyCommand depositMoneyCommand = new DepositMoneyCommand(walletId, null);

        //when
        CommandInvalidException result = Assertions.assertThrows(CommandInvalidException.class, () -> commandBus.handle(depositMoneyCommand));

        //then
        assertThat(result.getConstraintViolations()).isNotEmpty();
        verify(walletOperations, never()).deposit(walletId, null);
        verify(walletOperations, never()).deposit(eq(walletId), any(Money.class));
    }

    @Test
    void withdrawMoneyCommandInvalid() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        WithdrawMoneyCommand command = new WithdrawMoneyCommand(walletId, null);

        //when
        CommandInvalidException result = Assertions.assertThrows(CommandInvalidException.class, () -> commandBus.handle(command));

        //then
        assertThat(result.getConstraintViolations()).isNotEmpty();
        verify(walletOperations, never()).withdraw(walletId, null);
        verify(walletOperations, never()).withdraw(eq(walletId), any(Money.class));
    }

    @Test
    void transferMoneyCommandInvalid() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        Wallet.WalletId targetWalletId = new Wallet.WalletId("alias02", "user02");
        TransferMoneyCommand withdrawMoneyCommand = new TransferMoneyCommand(walletId,  targetWalletId, null);

        //when
        CommandInvalidException result = Assertions.assertThrows(CommandInvalidException.class, () -> commandBus.handle(withdrawMoneyCommand));

        //then
        assertThat(result.getConstraintViolations()).isNotEmpty();
        verify(walletOperations, never()).withdraw(walletId, null);
        verify(walletOperations, never()).withdraw(eq(walletId), any(Money.class));
    }
}