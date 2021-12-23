package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.commands.Query;
import com.aminabla.wallet.application.handlers.QueryHandler;
import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.ports.api.WalletInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QueryWalletBalanceCommandHandler implements QueryHandler<Balance, QueryWalletBalanceCommand> {

    private final WalletInfo walletInfoService;

    public QueryWalletBalanceCommandHandler(WalletInfo walletInfoService) {
        this.walletInfoService = walletInfoService;
    }

    @Override
    public Balance handle(QueryWalletBalanceCommand query) {
        log.debug("Retrieving balance from wallet '{}'", query.getWalletId().walletAlias());
        return walletInfoService.getBalance(query.getWalletId());
    }

    @Override
    public <T, C extends Query<T>> boolean canHandle(C command) {
        return QueryWalletBalanceCommand.class.isAssignableFrom(command.getClass());
    }
}
