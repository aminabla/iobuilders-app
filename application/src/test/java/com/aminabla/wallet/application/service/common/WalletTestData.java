package com.aminabla.wallet.application.service.common;

import com.aminabla.wallet.domain.User.UserId;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.Wallet.WalletId;
import com.aminabla.wallet.domain.WalletOperation;

import java.util.ArrayList;
import java.util.List;

public class WalletTestData {

    public static WalletBuilder defaultAccount() {
        return new WalletBuilder()
                .withId(new WalletId(0L));
    }


    public static class WalletBuilder {

        private WalletId id;
        private UserId userId;
        private List<WalletOperation> walletOperationList;

        public WalletBuilder() {
            this.walletOperationList = new ArrayList<>();
        }

        public WalletBuilder withId(WalletId walletId) {
            this.id = walletId;
            return this;
        }

        public WalletBuilder withUserId(UserId userId) {
            this.userId = userId;
            return this;
        }

        public WalletBuilder withOperation(WalletOperation walletOperation){
            walletOperationList.add(walletOperation);
            return this;
        }

        public Wallet build() {
            return new Wallet(this.id, userId, walletOperationList);
        }

    }


}
