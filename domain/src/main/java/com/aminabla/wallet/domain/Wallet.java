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

    @Getter
    private UserId userId;

    private List<WalletOperation> walletOperations;

    public Wallet(WalletId id, UserId userId, List<WalletOperation> walletOperations) {
        this.id = id;
        this.userId = userId;
        this.walletOperations = new ArrayList<>(walletOperations);
    }

    public Wallet(UserId userId, List<WalletOperation> walletOperations) {
        this(null, userId, walletOperations);
    }

    public Wallet(UserId userId) {
        this(userId, Collections.emptyList());
    }

    public boolean withdraw(Amount amount, WalletId targetwalletId) {
        if(!mayWithdraw(amount)) {
            return false;
        }
        walletOperations.add(new WalletOperation(this.id, targetwalletId, amount.negate()));
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


    public void deposit(WalletId sourcewalletId, Amount amount) {
        WalletOperation walletOperation = new WalletOperation(sourcewalletId, this.id, amount);
        this.walletOperations.add(walletOperation);
    }

    private boolean mayWithdraw(Amount money) {
        return balance().minus(money).isPositiveOrZero();
    }

    @Value
    public static class WalletId implements Identifier<Long>{
        private Long identifier;

    }

}