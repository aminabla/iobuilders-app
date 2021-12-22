package com.aminabla.wallet.application.handlers;

import com.aminabla.wallet.application.exception.CommandHandleException;

public interface CommandHandler<T> extends CommandHandlerCapable {
    void handle(T command) throws CommandHandleException;
}
