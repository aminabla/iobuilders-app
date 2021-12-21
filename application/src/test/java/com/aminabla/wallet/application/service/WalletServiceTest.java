package com.aminabla.wallet.application.service;

import com.aminabla.wallet.application.exception.WalletNotFoundException;
import com.aminabla.wallet.application.ports.api.commands.CreateWalletCommand;
import com.aminabla.wallet.application.ports.api.commands.DepositMoneyCommand;
import com.aminabla.wallet.application.ports.api.commands.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.ports.api.commands.WithdrawMoneyCommand;
import com.aminabla.wallet.application.ports.spi.LoadWalletPort;
import com.aminabla.wallet.application.ports.spi.WalletStatePort;
import com.aminabla.wallet.application.service.common.AccountOperationTestData;
import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.User;
import com.aminabla.wallet.domain.Wallet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.aminabla.wallet.application.service.common.WalletTestData.defaultAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {

    @Mock
    LoadWalletPort loadWalletPort;

    @Mock
    WalletStatePort walletStatePort;

    @InjectMocks
    WalletService walletService;

    @Test
    void walletNotFound() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId(1L);
        WithdrawMoneyCommand command = new WithdrawMoneyCommand(walletId, new Wallet.WalletId(2L), Amount.of(100));
        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.empty());

        //when/then
        Throwable error = Assertions.assertThrows(WalletNotFoundException.class,
                () -> walletService.withdraw(command));

        //then
        assertThat(error.getMessage()).isEqualTo(String.format("Wallet: %s not found", walletId));

    }

    @Test
    void withdraw() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId(1L);
        WithdrawMoneyCommand command = new WithdrawMoneyCommand(walletId, new Wallet.WalletId(2L), Amount.of(100));
        Wallet wallet = defaultAccount().
                withId(walletId)
                .withOperation(
                        new AccountOperationTestData.AccountOperationBuilder()
                                .withTargetAccount(walletId)
                                .withSourceAccount(new Wallet.WalletId(2L))
                                .withMoney(Amount.of(100)).build())
                .withOperation(
                        new AccountOperationTestData.AccountOperationBuilder()
                                .withTargetAccount(walletId)
                                .withSourceAccount(new Wallet.WalletId(2L))
                                .withMoney(Amount.of(100)).build()
                ).build();
        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.of(wallet));

        //when
        walletService.withdraw(command);

        //then
        verify(loadWalletPort, times(1)).loadWallet(eq(walletId));
        verify(walletStatePort, times(1)).update(eq(wallet));
        assertThat(wallet.balance()).isEqualTo(Amount.of(100));
    }

    @Test
    void deposit() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId(1L);
        DepositMoneyCommand command = new DepositMoneyCommand(new Wallet.WalletId(2L), walletId, Amount.of(100));
        Wallet wallet = defaultAccount().
                withId(walletId)
                .withOperation(
                        new AccountOperationTestData.AccountOperationBuilder()
                                .withTargetAccount(walletId)
                                .withSourceAccount(new Wallet.WalletId(2L))
                                .withMoney(Amount.of(100)).build())
                .withOperation(
                        new AccountOperationTestData.AccountOperationBuilder()
                                .withTargetAccount(walletId)
                                .withSourceAccount(new Wallet.WalletId(2L))
                                .withMoney(Amount.of(100)).build()
                ).build();
        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.of(wallet));

        //when
        walletService.deposit(command);

        //then
        verify(loadWalletPort, times(1)).loadWallet(eq(walletId));
        verify(walletStatePort, times(1)).update(eq(wallet));
        assertThat(wallet.balance()).isEqualTo(Amount.of(300));
    }

    @Test
    void create() {
        //given
        User user = new User(new User.UserId("aaa11111"));
        CreateWalletCommand command = new CreateWalletCommand(user);

        //when
        walletService.create(command);

        //then
        Mockito.verify(walletStatePort, times(1)).create(eq(user));
    }
}