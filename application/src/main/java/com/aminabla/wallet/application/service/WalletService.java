package com.aminabla.wallet.application.service;

import com.aminabla.wallet.application.exception.WalletNotFoundException;
import com.aminabla.wallet.application.ports.api.WalletOperations;
import com.aminabla.wallet.application.commands.CreateWalletCommand;
import com.aminabla.wallet.application.commands.DepositMoneyCommand;
import com.aminabla.wallet.application.commands.WithdrawMoneyCommand;
import com.aminabla.wallet.application.ports.spi.LoadWalletPort;
import com.aminabla.wallet.application.ports.spi.WalletStatePort;
import com.aminabla.wallet.domain.Wallet;

import java.util.Optional;
import java.util.function.Supplier;

public class WalletService implements WalletOperations {

    private final LoadWalletPort loadWalletPort;

    private final WalletStatePort walletStatePort;

    public WalletService(LoadWalletPort loadWalletPort, WalletStatePort walletStatePort) {
        this.loadWalletPort = loadWalletPort;
        this.walletStatePort = walletStatePort;
    }


    @Override
    public void withdraw(WithdrawMoneyCommand command) {

        getWallet(command.getWalletId())
                .ifPresentOrElse(
                        wallet -> doWithdraw(wallet, command),
                        () -> walletNotFound(command::getWalletId)
                );
    }

    private void doWithdraw(Wallet wallet, WithdrawMoneyCommand command) {
        wallet.withdraw(command.getAmount());
        walletStatePort.update(wallet);
    }


    @Override
    public void deposit(DepositMoneyCommand command) {
        getWallet(command.getWalletId())
                .ifPresentOrElse(
                        wallet -> doDeposit(wallet, command),
                        () -> walletNotFound(command::getWalletId)
                );
    }

    private void doDeposit(Wallet wallet, DepositMoneyCommand command) {
        wallet.deposit(command.getAmount());
        walletStatePort.update(wallet);
    }

    @Override
    public void create(CreateWalletCommand createWalletCommand) {
        walletStatePort.create(createWalletCommand.getWalletId());
    }


    private Optional<Wallet> getWallet(Wallet.WalletId walletId) {
        return loadWalletPort.loadWallet(walletId);
    }

    private void walletNotFound(Supplier<Wallet.WalletId> supplier){
        throw new WalletNotFoundException(String.format("Wallet: %s not found", supplier.get()));
    }
}




