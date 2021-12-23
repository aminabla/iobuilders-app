package com.aminabla.wallet.application.buses;

public interface CommandValidator<T> {
    void validate(T command);
}
