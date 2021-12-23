package com.aminabla.wallet.domain;

import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.domain.common.WalletOperationTestData.WalletOperationBuilder;
import org.junit.jupiter.api.Test;

import static com.aminabla.wallet.domain.common.WalletTestData.defaultAccount;
import static org.assertj.core.api.Assertions.assertThat;

class WalletTest {

    @Test
    void withdrawalSucceeds() {
        WalletId walletId = new WalletId("alias", "user");
        Wallet wallet = defaultAccount()
                .withId(walletId)
                .withOperation(new WalletOperationBuilder()
                        .withWalletId(walletId)
                        .withMoney(Money.of(455)).build())
                .withOperation(new WalletOperationBuilder()
                        .withWalletId(walletId)
                        .withMoney(Money.of(100)).build())
                .build();

        boolean success = wallet.withdraw(Money.of(555L));

        assertThat(success).isTrue();
        assertThat(wallet.balance()).isEqualTo(Money.of(0L));
    }

    @Test
    void withdrawalFailure() {
        WalletId walletId = new WalletId("alias", "user");
        Wallet wallet = defaultAccount()
                .withId(walletId)
                .withOperation(new WalletOperationBuilder()
                        .withWalletId(walletId)
                        .withMoney(Money.of(455)).build())
                .withOperation(new WalletOperationBuilder()
                        .withWalletId(walletId)
                        .withMoney(Money.of(100)).build())
                .build();

        boolean success = wallet.withdraw(Money.of(1556L));

        assertThat(success).isFalse();
        assertThat(wallet.balance()).isEqualTo(Money.of(555L));
    }

    @Test
    void depositSuccess() {
        WalletId walletId = new WalletId("alias", "user");
        Wallet wallet = defaultAccount()
                .withId(walletId)
                .withOperation(new WalletOperationBuilder()
                        .withWalletId(walletId)
                        .withMoney(Money.of(455)).build())
                .withOperation(new WalletOperationBuilder()
                        .withWalletId(walletId)
                        .withMoney(Money.of(100)).build())
                .build();

        wallet.deposit(Money.of(445L));
        assertThat(wallet.balance()).isEqualTo(Money.of(1000L));
    }

}