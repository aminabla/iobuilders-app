package com.aminabla.wallet.application.buses;

import com.aminabla.wallet.application.commands.Command;

public interface CommandBus {

    <T extends Command> void handle(T command);
}
