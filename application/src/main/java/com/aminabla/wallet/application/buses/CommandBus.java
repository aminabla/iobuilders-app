package com.aminabla.wallet.application.buses;

public interface CommandBus<T> {

    void handle(T command);
}
