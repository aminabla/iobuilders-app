package com.aminabla.wallet.domain;

import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.domain.common.WalletOperationTestData.WalletOperationBuilder;
import org.junit.jupiter.api.Test;

import static com.aminabla.wallet.domain.common.WalletTestData.defaultAccount;
import static org.assertj.core.api.Assertions.assertThat;

class WalletTest {

    @Test
    void withdrawalSucceeds() {
        WalletId walletId = new WalletId(1L);
        Wallet wallet = defaultAccount()
                .withId(walletId)
                .withOperation(new WalletOperationBuilder()
                        .withTargetAccount(walletId)
                        .withSourceAccount(new WalletId(2L))
                        .withMoney(Amount.of(455)).build())
                .withOperation(new WalletOperationBuilder()
                        .withTargetAccount(walletId)
                        .withSourceAccount(new WalletId(2L))
                        .withMoney(Amount.of(100)).build())
                .build();

        boolean success = wallet.withdraw(Amount.of(555L), new WalletId(3L));

        assertThat(success).isTrue();
        assertThat(wallet.balance()).isEqualTo(Amount.of(0L));
    }

    @Test
    void withdrawalFailure() {
        WalletId walletId = new WalletId(1L);
        Wallet wallet = defaultAccount()
                .withId(walletId)
                .withOperation(new WalletOperationBuilder()
                        .withTargetAccount(walletId)
                        .withSourceAccount(new WalletId(2L))
                        .withMoney(Amount.of(455)).build())
                .withOperation(new WalletOperationBuilder()
                        .withTargetAccount(walletId)
                        .withSourceAccount(new WalletId(2L))
                        .withMoney(Amount.of(100)).build())
                .build();

        boolean success = wallet.withdraw(Amount.of(1556L), new WalletId(2L));

        assertThat(success).isFalse();
        assertThat(wallet.balance()).isEqualTo(Amount.of(555L));
    }

    @Test
    void depositSuccess() {
        WalletId walletId = new WalletId(1L);
        Wallet wallet = defaultAccount()
                .withId(walletId)
                .withOperation(new WalletOperationBuilder()
                        .withTargetAccount(walletId)
                        .withSourceAccount(new WalletId(2L))
                        .withMoney(Amount.of(455)).build())
                .withOperation(new WalletOperationBuilder()
                        .withTargetAccount(walletId)
                        .withSourceAccount(new WalletId(2L))
                        .withMoney(Amount.of(100)).build())
                .build();

        wallet.deposit(new WalletId(2L), Amount.of(445L));
        assertThat(wallet.balance()).isEqualTo(Amount.of(1000L));
    }

}