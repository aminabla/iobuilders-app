package com.aminabla.wallet.application.handlers;

public interface QueryHandlerCapable {
    <T, C extends Query<T>> boolean canHandle(C command);
}
