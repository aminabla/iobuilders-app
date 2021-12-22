package com.aminabla.wallet.domain.common;

import com.aminabla.wallet.domain.User.UserId;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.domain.WalletOperation;

import java.util.ArrayList;
import java.util.List;

public class WalletTestData {

    public static WalletBuilder defaultAccount() {
        return new WalletBuilder()
                .withId(new WalletId("alias", "user"));
    }


    public static class WalletBuilder {

        private WalletId id;
        private List<WalletOperation> walletOperationList;

        public WalletBuilder() {
            this.walletOperationList = new ArrayList<>();
        }

        public WalletBuilder withId(WalletId walletId) {
            this.id = walletId;
            return this;
        }

        public WalletBuilder withOperation(WalletOperation walletOperation){
            walletOperationList.add(walletOperation);
            return this;
        }

        public Wallet build() {
            return new Wallet(this.id, walletOperationList);
        }

    }


}
