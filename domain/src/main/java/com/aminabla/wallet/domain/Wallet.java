package com.aminabla.wallet.domain;


import com.aminabla.wallet.domain.User.UserId;
import lombok.Getter;
import lombok.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wallet {

    @Getter
    private final WalletId id;

    private List<WalletOperation> walletOperations;

    public Wallet(WalletId id, List<WalletOperation> walletOperations) {
        this.id = id;
        this.walletOperations = new ArrayList<>(walletOperations);
    }

    public Wallet(WalletId walletId) {
        this(walletId, Collections.emptyList());
    }

    public boolean withdraw(Amount amount) {
        if(!mayWithdraw(amount)) {
            return false;
        }
        walletOperations.add(new WalletOperation(this.id, amount.negate()));
        return true;
    }

    public Amount balance(){
        return walletOperations.stream()
                .map(WalletOperation::getAmount)
                .reduce(Amount.ZERO, Amount::plus);
    }

    public List<WalletOperation> history(){
        return walletOperations.stream().toList();
    }


    public void deposit(Amount amount) {
        WalletOperation walletOperation = new WalletOperation(this.id, amount);
        this.walletOperations.add(walletOperation);
    }

    private boolean mayWithdraw(Amount money) {
        return balance().minus(money).isPositiveOrZero();
    }

    @Value
    public static class WalletId{
        private String walletAlias;
        private String ownerId;
    }

}