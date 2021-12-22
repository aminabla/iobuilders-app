package com.aminabla.wallet.application.buses.impl;

import com.aminabla.wallet.application.buses.CommandBus;
import com.aminabla.wallet.application.handlers.Command;
import com.aminabla.wallet.application.handlers.CommandHandler;

import java.util.List;

public class CommandBusImpl implements CommandBus {


    private final List<CommandHandler<? super Command>> handlers;

    public CommandBusImpl(List<CommandHandler<? super Command>> handlers) {
        this.handlers = handlers;
    }


    @Override
    public <T extends Command> void handle(T command) {
        getHandler(command).handle(command);
    }

    private CommandHandler<? super Command> getHandler(Command command){
        return handlers.stream()
                .filter(handler -> handler.canHandle(command))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No valid handler found for command" + command.getClass().getSimpleName()));
    }
}
