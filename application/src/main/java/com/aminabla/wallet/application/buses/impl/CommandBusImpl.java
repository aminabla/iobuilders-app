package com.aminabla.wallet.application.buses.impl;

import com.aminabla.wallet.application.buses.CommandBus;
import com.aminabla.wallet.application.buses.CommandValidator;
import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.application.handlers.CommandHandler;

import java.util.List;

public class CommandBusImpl<T extends Command> implements CommandBus<T> {


    private final List<CommandHandler<T>> handlers;

    private final CommandValidator<T> validator;

    public CommandBusImpl(List<CommandHandler<T>> handlers, CommandValidator<T> validator) {
        this.handlers = handlers;
        this.validator = validator;
    }


    @Override
    public void handle(T command) {
            validator.validate(command);
        getHandler(command).handle(command);
    }

    private CommandHandler<T> getHandler(Command command){
        return handlers.stream()
                .filter(handler -> handler.canHandle(command))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No valid handler found for command: " + command.getClass().getSimpleName()));
    }
}
