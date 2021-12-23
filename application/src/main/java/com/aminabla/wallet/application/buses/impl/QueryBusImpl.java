package com.aminabla.wallet.application.buses.impl;

import com.aminabla.wallet.application.buses.CommandValidator;
import com.aminabla.wallet.application.buses.QueryBus;
import com.aminabla.wallet.application.commands.Query;
import com.aminabla.wallet.application.handlers.QueryHandler;

import java.util.List;

public class QueryBusImpl implements QueryBus {

    private final List<QueryHandler<?, ? extends Query<?>>> handlers;

    private final CommandValidator<Query> validator;

    public QueryBusImpl(List<QueryHandler<?, ? extends Query<?>>> handlers, CommandValidator<Query> validator) {
        this.handlers = handlers;
        this.validator = validator;
    }

    @Override
    public <T, Q extends Query<T>> T handle(Q query) {
        validator.validate(query);
        return getHandler(query).handle(query);
    }

    private <T, Q extends Query<T>> QueryHandler<T, Q> getHandler(Q query) {
        return handlers.stream()
                .filter(handler -> handler.canHandle(query))
                .findFirst()
                .map(QueryHandler.class::cast)
                .orElseThrow(() -> new IllegalStateException("No valid handler found for command" + query.getClass().getSimpleName()));
    }
}
