package com.aminabla.wallet.application.service;

import com.aminabla.wallet.application.exception.WalletNotFoundException;
import com.aminabla.wallet.application.ports.api.WalletOperations;
import com.aminabla.wallet.application.ports.api.commands.CreateWalletCommand;
import com.aminabla.wallet.application.ports.api.commands.DepositMoneyCommand;
import com.aminabla.wallet.application.ports.api.commands.WithdrawMoneyCommand;
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

        getWallet(command.getSourceAccountId())
                .ifPresentOrElse(
                        wallet -> doWithdraw(wallet, command),
                        () -> walletNotFound(command::getSourceAccountId)
                );
    }

    private void doWithdraw(Wallet wallet, WithdrawMoneyCommand command) {
        wallet.withdraw(command.getAmount(), command.getTargetAccountId());
        walletStatePort.update(wallet);
    }


    @Override
    public void deposit(DepositMoneyCommand command) {
        getWallet(command.getTargetAccountId())
                .ifPresentOrElse(
                        wallet -> doDeposit(wallet, command),
                        () -> walletNotFound(command::getTargetAccountId)
                );
    }

    private void doDeposit(Wallet wallet, DepositMoneyCommand command) {
        wallet.deposit(command.getSourceAccountId(), command.getAmount());
        walletStatePort.update(wallet);
    }

    @Override
    public Wallet create(CreateWalletCommand createWalletCommand) {
        return walletStatePort.create(createWalletCommand.getUser());
    }


    private Optional<Wallet> getWallet(Wallet.WalletId walletId) {
        return loadWalletPort.loadWallet(walletId);
    }

    private void walletNotFound(Supplier<Wallet.WalletId> supplier){
        throw new WalletNotFoundException(String.format("Wallet: %s not found", supplier.get()));
    }
}




