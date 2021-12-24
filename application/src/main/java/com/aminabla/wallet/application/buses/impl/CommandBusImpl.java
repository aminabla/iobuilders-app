package com.aminabla.wallet.application.buses.impl;

import com.aminabla.wallet.application.buses.CommandBus;
import com.aminabla.wallet.application.buses.CommandValidator;
import com.aminabla.wallet.application.commands.Command;
import com.aminabla.wallet.application.handlers.CommandHandler;

import java.util.List;

public class CommandBusImpl implements CommandBus {


    private final List<CommandHandler<? extends Command>> handlers;

    private final CommandValidator<Command> validator;

    public CommandBusImpl(List<CommandHandler<? extends Command>> handlers, CommandValidator<Command> validator) {
        this.handlers = handlers;
        this.validator = validator;
    }


    @Override
    public <T extends Command> void handle(T command) {
        validator.validate(command);
        getHandler(command).handle(command);
    }

    private <T extends Command> CommandHandler<T> getHandler(Command command) {
        return handlers.stream()
                .filter(handler -> handler.canHandle(command))
                .findFirst()
                .map(CommandHandler.class::cast)
                .orElseThrow(() -> new IllegalStateException("No valid handler found for command: " + command.getClass().getSimpleName()));
    }
}
