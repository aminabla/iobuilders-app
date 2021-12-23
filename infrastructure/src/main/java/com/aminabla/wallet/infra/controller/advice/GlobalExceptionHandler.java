package com.aminabla.wallet.infra.controller.advice;

import com.aminabla.wallet.application.exception.CommandInvalidException;
import com.aminabla.wallet.domain.exception.NotEnoughMoneyException;
import com.aminabla.wallet.domain.exception.WalletNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity globalError(Exception exception) {
        return new ResponseEntity<>("Unexpected Error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = WalletNotFoundException.class)
    public ResponseEntity walletNotFound(WalletNotFoundException exception) {
        return new ResponseEntity<>("Bad request. Requested Wallet does not exists", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NotEnoughMoneyException.class)
    public ResponseEntity notEnoughMoney(NotEnoughMoneyException exception) {
        return new ResponseEntity<>("Bad request. Not enough money", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = CommandInvalidException.class)
    public ResponseEntity commandInvalidException(CommandInvalidException exception) {
        return new ResponseEntity<>("Bad request. " + exception.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(",")), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<String> errorList = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> format("%s:%s", fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return new ResponseEntity(errorList, HttpStatus.BAD_REQUEST);
    }
}

