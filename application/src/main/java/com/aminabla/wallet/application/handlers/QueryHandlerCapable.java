package com.aminabla.wallet.application.handlers;

import com.aminabla.wallet.application.commands.Query;

public interface QueryHandlerCapable {
    <T, C extends Query<T>> boolean canHandle(C command);
}
