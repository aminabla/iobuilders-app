package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.handlers.Query;
import com.aminabla.wallet.application.handlers.QueryHandler;
import com.aminabla.wallet.application.ports.api.WalletInfo;
import com.aminabla.wallet.application.service.WalletInfoService;
import com.aminabla.wallet.application.service.WalletService;
import com.aminabla.wallet.domain.Balance;

public class QueryWalletBalanceCommandHandler implements QueryHandler<Balance, QueryWalletBalanceCommand> {

    private final WalletInfo walletInfoService;

    public QueryWalletBalanceCommandHandler(WalletInfo walletInfoService) {
        this.walletInfoService = walletInfoService;
    }

    @Override
    public Balance handle(QueryWalletBalanceCommand query) {
        return walletInfoService.getBalance(query);
    }

    @Override
    public <T, C extends Query<T>> boolean canHandle(C command) {
        return QueryWalletBalanceCommand.class.isAssignableFrom(command.getClass());
    }
}
