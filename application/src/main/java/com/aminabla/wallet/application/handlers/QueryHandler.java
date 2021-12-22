package com.aminabla.wallet.application.handlers;

public interface QueryHandler<T,U extends Query<T>> extends QueryHandlerCapable{
    T handle(U query);
}
