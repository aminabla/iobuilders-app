package com.aminabla.wallet.application.buses.impl;

import com.aminabla.wallet.application.buses.QueryBus;
import com.aminabla.wallet.application.handlers.Query;
import com.aminabla.wallet.application.handlers.QueryHandler;

import java.util.List;

public class QueryBusImpl implements QueryBus {

   private final List<QueryHandler<?, ? super Query<?>>> handlers;

    public QueryBusImpl(List<QueryHandler<?, ? super Query<?>>> handlers) {
        this.handlers = handlers;
    }

    @Override
    public <T> T handle(Query<T> query) {
        return (T) getHandler(query).handle(query);
    }

    private QueryHandler<?, ? super Query<?>> getHandler(Query<?> command){
        return handlers.stream()
                .filter(handler -> handler.canHandle(command))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No valid handler found for command" + command.getClass().getSimpleName()));
    }


}
