package com.aminabla.wallet.domain.service;

import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.domain.common.WalletOperationTestData.WalletOperationBuilder;
import com.aminabla.wallet.domain.exception.WalletNotFoundException;
import com.aminabla.wallet.domain.ports.spi.LoadWalletPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.aminabla.wallet.domain.common.WalletTestData.defaultAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletInfoServiceTest {

    @Mock
    LoadWalletPort loadWalletPort;

    @InjectMocks
    WalletInfoService walletInfoService;

    @Test
    void walletNotFound() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        Double expectedBalance = 200D;

        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.empty());

        //when/then
        Throwable error = Assertions.assertThrows(WalletNotFoundException.class,
                () -> walletInfoService.getBalance(walletId)
        );

        //then
        assertThat(error.getMessage()).isEqualTo(String.format("Wallet: %s not found", walletId));

    }

    @Test
    void balance() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        Double expectedBalance = 200D;
        Wallet wallet = defaultAccount().
                withId(walletId)
                .withOperation(
                        new WalletOperationBuilder()
                                .withWalletId(walletId)
                                .withMoney(Money.of(expectedBalance/2)).build())
                .withOperation(
                        new WalletOperationBuilder()
                                .withWalletId(walletId)
                                .withMoney(Money.of(expectedBalance/2)).build()
                ).build();


        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.of(wallet));

        //when
        Balance balance = walletInfoService.getBalance(walletId);

        //then
        assertThat(balance).isNotNull();
        assertThat(balance.getId()).isEqualTo(walletId);
        assertThat(balance.getAmount()).isEqualTo(Money.of(expectedBalance));

    }


    @Test
    void history() {
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
        List<WalletOperation> ops = walletInfoService.getOperationsHistory(walletId);

        //then
        assertThat(ops).isNotEmpty();
        assertThat(ops.size()).isEqualTo(2);

    }


    @Test
    void historyEmpty() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId("alias", "user");
        Wallet wallet = defaultAccount().
                withId(walletId)
                .build();

        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.of(wallet));

        //when
        List<WalletOperation> ops = walletInfoService.getOperationsHistory(walletId);

        //then
        assertThat(ops).isEmpty();

    }

}