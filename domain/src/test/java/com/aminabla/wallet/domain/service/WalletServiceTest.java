package com.aminabla.wallet.domain.service;


import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.common.WalletOperationTestData.WalletOperationBuilder;
import com.aminabla.wallet.domain.exception.WalletNotFoundException;
import com.aminabla.wallet.domain.ports.spi.LoadWalletPort;
import com.aminabla.wallet.domain.ports.spi.WalletStatePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.aminabla.wallet.domain.common.WalletTestData.defaultAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
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
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        Money amount = Money.of(100);
        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.empty());

        //when/then
        Throwable error = Assertions.assertThrows(WalletNotFoundException.class,
                () -> walletService.withdraw(walletId, amount));

        //then
        assertThat(error.getMessage()).isEqualTo(String.format("Wallet: %s not found", walletId));

    }

    @Test
    void withdraw() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        Wallet wallet = defaultAccount().
                withId(walletId)
                .withOperation(
                        new WalletOperationBuilder()
                                .withWalletId(walletId)
                                .withMoney(Money.of(100)).build())
                .withOperation(
                        new WalletOperationBuilder()
                                .withWalletId(walletId)
                                .withMoney(Money.of(100)).build()
                ).build();
        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.of(wallet));

        //when
        walletService.withdraw(walletId, Money.of(100));

        //then
        verify(loadWalletPort, times(1)).loadWallet(walletId);
        verify(walletStatePort, times(1)).update(wallet);
        assertThat(wallet.balance()).isEqualTo(Money.of(100));
    }

    @Test
    void deposit() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        Wallet wallet = defaultAccount().
                withId(walletId)
                .withOperation(
                        new WalletOperationBuilder()
                                .withWalletId(walletId)
                                .withMoney(Money.of(100)).build())
                .withOperation(
                        new WalletOperationBuilder()
                                .withWalletId(walletId)
                                .withMoney(Money.of(100)).build()
                ).build();
        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.of(wallet));

        //when
        walletService.deposit(walletId, Money.of(100));

        //then
        verify(loadWalletPort, times(1)).loadWallet(walletId);
        verify(walletStatePort, times(1)).update(wallet);
        assertThat(wallet.balance()).isEqualTo(Money.of(300));
    }

    @Test
    void create() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");

        //when
        walletService.create(walletId);

        //then
        Mockito.verify(walletStatePort, times(1)).create(walletId);
    }
}