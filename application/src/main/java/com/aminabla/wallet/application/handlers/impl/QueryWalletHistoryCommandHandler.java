package com.aminabla.wallet.application.handlers.impl;

import com.aminabla.wallet.application.commands.impl.QueryWalletHistoryCommand;
import com.aminabla.wallet.application.commands.Query;
import com.aminabla.wallet.application.handlers.QueryHandler;
import com.aminabla.wallet.domain.WalletOperation;
import com.aminabla.wallet.domain.ports.api.WalletInfo;

import java.util.List;

public class QueryWalletHistoryCommandHandler implements QueryHandler<List<WalletOperation>, QueryWalletHistoryCommand> {

    private final WalletInfo walletInfoService;

    public QueryWalletHistoryCommandHandler(WalletInfo walletInfoService) {
        this.walletInfoService = walletInfoService;
    }

    @Override
    public List<WalletOperation> handle(QueryWalletHistoryCommand query){
        return walletInfoService.getOperationsHistory(query.getWalletId());
    }

    @Override
    public <T, C extends Query<T>> boolean canHandle(C command) {
        return QueryWalletHistoryCommand.class.isAssignableFrom(command.getClass());
    }
}
