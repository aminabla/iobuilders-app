package com.aminabla.wallet.application.handlers;

import com.aminabla.wallet.application.commands.Query;

public interface QueryHandler<T,Q extends Query<T>> extends QueryHandlerCapable{
    T handle(Q query);
}
