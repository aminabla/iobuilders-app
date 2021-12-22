package com.aminabla.wallet.application.handlers;

public interface CommandHandlerCapable {
    <C extends Command> boolean canHandle(C command);
}
