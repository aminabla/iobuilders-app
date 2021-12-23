package com.aminabla.wallet.application.buses.impl;

import com.aminabla.wallet.application.buses.CommandValidator;
import com.aminabla.wallet.application.exception.CommandInvalidException;

import javax.validation.*;
import java.util.Set;

public class ValidatorImpl<T> implements CommandValidator<T> {

    private Validator validator;

    public ValidatorImpl() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * Evaluates all Bean Validations on the attributes of this
     * instance.
     */
    public void validate(T command) {
        Set<ConstraintViolation<T>> violations = validator.validate(command);
        if (!violations.isEmpty()) {
            throw new CommandInvalidException(violations);
        }
    }
}
