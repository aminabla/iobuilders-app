package com.aminabla.wallet.application.exception;

public class CommandHandleException extends RuntimeException{
    public CommandHandleException(String message) {
        super(message);
    }

    public CommandHandleException(String message, Throwable cause) {
        super(message, cause);
    }
}
