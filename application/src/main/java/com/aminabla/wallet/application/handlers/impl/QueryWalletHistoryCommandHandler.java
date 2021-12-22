package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.QueryWalletBalanceCommand;
import com.aminabla.wallet.application.commands.QueryWalletHistoryCommand;
import com.aminabla.wallet.application.handlers.Query;
import com.aminabla.wallet.application.handlers.QueryHandler;
import com.aminabla.wallet.application.ports.api.WalletInfo;
import com.aminabla.wallet.application.service.WalletInfoService;
import com.aminabla.wallet.domain.Balance;
import com.aminabla.wallet.domain.WalletOperation;

import java.util.List;
import java.util.ListIterator;

public class QueryWalletHistoryCommandHandler implements QueryHandler<List<WalletOperation>, QueryWalletHistoryCommand> {

    private final WalletInfo walletInfoService;

    public QueryWalletHistoryCommandHandler(WalletInfo walletInfoService) {
        this.walletInfoService = walletInfoService;
    }

    @Override
    public List<WalletOperation> handle(QueryWalletHistoryCommand query){
        return walletInfoService.getOperationsHistory(query);
    }

    @Override
    public <T, C extends Query<T>> boolean canHandle(C command) {
        return QueryWalletHistoryCommand.class.isAssignableFrom(command.getClass());
    }
}
