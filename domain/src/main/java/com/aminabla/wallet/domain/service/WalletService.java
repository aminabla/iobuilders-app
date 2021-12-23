package com.aminabla.wallet.domain.service;

import com.aminabla.wallet.domain.Money;
import com.aminabla.wallet.domain.Wallet;
import com.aminabla.wallet.domain.exception.NotEnoughMoneyException;
import com.aminabla.wallet.domain.exception.WalletNotFoundException;
import com.aminabla.wallet.domain.ports.api.WalletOperations;
import com.aminabla.wallet.domain.ports.spi.LoadWalletPort;
import com.aminabla.wallet.domain.ports.spi.WalletStatePort;

import java.util.Optional;
import java.util.function.Supplier;

import static java.lang.String.format;

public class WalletService implements WalletOperations {

    private final LoadWalletPort loadWalletPort;

    private final WalletStatePort walletStatePort;

    public WalletService(LoadWalletPort loadWalletPort, WalletStatePort walletStatePort) {
        this.loadWalletPort = loadWalletPort;
        this.walletStatePort = walletStatePort;
    }


    @Override
    public void withdraw(Wallet.WalletId walletId, Money amount) {

        getWallet(walletId)
                .ifPresentOrElse(
                        wallet -> doWithdraw(wallet, amount),
                        () -> walletNotFound(() -> walletId)
                );
    }

    private void doWithdraw(Wallet wallet, Money amount) {
        if(wallet.withdraw(amount)){
            walletStatePort.update(wallet);
        }else {
            throw new NotEnoughMoneyException(format("Not enough money to withdraw: %s", amount.getAmount()));
        }

    }


    @Override
    public void deposit(Wallet.WalletId walletId, Money amount) {
        getWallet(walletId)
                .ifPresentOrElse(
                        wallet -> doDeposit(wallet, amount),
                        () -> walletNotFound(() -> walletId)
                );
    }

    private void doDeposit(Wallet wallet, Money amount) {
        wallet.deposit(amount);
        walletStatePort.update(wallet);
    }

    @Override
    public void create(Wallet.WalletId walletId) {
        walletStatePort.create(walletId);
    }


    private Optional<Wallet> getWallet(Wallet.WalletId walletId) {
        return loadWalletPort.loadWallet(walletId);
    }

    private void walletNotFound(Supplier<Wallet.WalletId> supplier){
        throw new WalletNotFoundException(format("Wallet: %s not found", supplier.get()));
    }
}




