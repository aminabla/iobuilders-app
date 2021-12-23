package com.aminabla.wallet.domain;


import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Wallet {

    @Getter
    private final WalletId id;

    private final List<WalletOperation> walletOperations;

    public Wallet(WalletId id, List<WalletOperation> walletOperations) {
        this.id = id;
        this.walletOperations = new ArrayList<>(walletOperations);
    }

    public Wallet(WalletId walletId) {
        this(walletId, Collections.emptyList());
    }

    public boolean withdraw(Money amount) {
        if(!mayWithdraw(amount)) {
            return false;
        }
        walletOperations.add(new WalletOperation(this.id, amount.negate()));
        return true;
    }

    public Money balance(){
        return walletOperations.stream()
                .map(WalletOperation::getAmount)
                .reduce(Money.ZERO, Money::plus);
    }

    public List<WalletOperation> history(){
        return walletOperations.stream().toList();
    }


    public void deposit(Money amount) {
        WalletOperation walletOperation = new WalletOperation(this.id, amount);
        this.walletOperations.add(walletOperation);
    }

    private boolean mayWithdraw(Money money) {
        return balance().minus(money).isPositiveOrZero();
    }

    public record WalletId(String walletAlias, String ownerId) {
    }

}