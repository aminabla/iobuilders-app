package com.aminabla.wallet.infra.controller.advice;

import com.aminabla.wallet.application.exception.CommandInvalidException;
import com.aminabla.wallet.domain.exception.NotEnoughMoneyException;
import com.aminabla.wallet.domain.exception.WalletNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ Exception.class})
    public ResponseEntity<Object> handleUnexpected(final RuntimeException ex, final WebRequest request) {
        logger.error("Managing unexpected error on global exception handler", ex);
        final String body = "Unexpected error";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<Object> walletNotFound(WalletNotFoundException ex, WebRequest request) {
        final String body = "Bad request. Requested Wallet does not exists";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }



    @ExceptionHandler(value = NotEnoughMoneyException.class)
    public ResponseEntity<Object> notEnoughMoney(NotEnoughMoneyException ex, WebRequest request) {
        final String body = "Conflict. Not enough money";
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }


    @ExceptionHandler(value = CommandInvalidException.class)
    public ResponseEntity<Object> commandInvalidException(CommandInvalidException ex, WebRequest request) {
        final String body = "Bad request. " + ex.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(","));
        return handleExceptionInternal(ex, body, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}

