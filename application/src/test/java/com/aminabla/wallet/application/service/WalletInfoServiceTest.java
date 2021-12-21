package com.aminabla.wallet.application.service;

import com.aminabla.wallet.application.exception.WalletNotFoundException;
import com.aminabla.wallet.application.ports.api.commands.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.ports.api.commands.QueryWalletHistoryCommand;
import com.aminabla.wallet.application.ports.spi.LoadWalletPort;
import com.aminabla.wallet.application.service.common.AccountOperationTestData.AccountOperationBuilder;
import com.aminabla.wallet.domain.Amount;
import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.WalletOperation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.aminabla.wallet.application.service.common.WalletTestData.defaultAccount;
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
        Wallet.WalletId walletId = new Wallet.WalletId(1L);
        Double expectedBalance = 200D;

        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.empty());

        //when/then
        Throwable error = Assertions.assertThrows(WalletNotFoundException.class, () -> walletInfoService.getBalance(new QueryWalletBalanceCommand(walletId)));

        //then
        assertThat(error.getMessage()).isEqualTo(String.format("Wallet: %s not found", walletId));

    }

    @Test
    void balance() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId(1L);
        Double expectedBalance = 200D;
        Wallet wallet = defaultAccount().
                withId(walletId)
                .withOperation(
                        new AccountOperationBuilder()
                                .withTargetAccount(walletId)
                                .withSourceAccount(new Wallet.WalletId(2L))
                                .withMoney(Amount.of(expectedBalance/2)).build())
                .withOperation(
                        new AccountOperationBuilder()
                                .withTargetAccount(walletId)
                                .withSourceAccount(new Wallet.WalletId(2L))
                                .withMoney(Amount.of(expectedBalance/2)).build()
                ).build();


        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.of(wallet));

        //when
        Balance balance = walletInfoService.getBalance(new QueryWalletBalanceCommand(walletId));

        //then
        assertThat(balance).isNotNull();
        assertThat(balance.getId()).isEqualTo(walletId);
        assertThat(balance.getAmount()).isEqualTo(Amount.of(expectedBalance));

    }


    @Test
    void history() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId(1L);
        Wallet wallet = defaultAccount().
                withId(walletId)
                .withOperation(
                        new AccountOperationBuilder()
                                .withTargetAccount(walletId)
                                .withSourceAccount(new Wallet.WalletId(2L))
                                .withMoney(Amount.of(100)).build())
                .withOperation(
                        new AccountOperationBuilder()
                                .withTargetAccount(walletId)
                                .withSourceAccount(new Wallet.WalletId(2L))
                                .withMoney(Amount.of(100)).build()
                ).build();

        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.of(wallet));

        //when
        List<WalletOperation> ops = walletInfoService.getOperationsHistory(new QueryWalletHistoryCommand(walletId));

        //then
        assertThat(ops).isNotEmpty();
        assertThat(ops.size()).isEqualTo(2);

    }


    @Test
    void historyEmpty() {
        //given
        Wallet.WalletId walletId = new Wallet.WalletId(1L);
        Wallet wallet = defaultAccount().
                withId(walletId)
                .build();

        when(loadWalletPort.loadWallet(walletId)).thenReturn(Optional.of(wallet));

        //when
        List<WalletOperation> ops = walletInfoService.getOperationsHistory(new QueryWalletHistoryCommand(walletId));

        //then
        assertThat(ops).isEmpty();

    }

}