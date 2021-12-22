package com.aminabla.wallet.application.buses;

import com.aminabla.wallet.application.handlers.Command;

public interface CommandBus {

    <T extends Command> void handle(T command);
}
