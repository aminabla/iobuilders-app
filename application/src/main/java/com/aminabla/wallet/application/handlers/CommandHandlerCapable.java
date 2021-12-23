package com.aminabla.wallet.application.handlers;

import com.aminabla.wallet.application.commands.Command;

public interface CommandHandlerCapable {
    <C extends Command> boolean canHandle(C command);
}
