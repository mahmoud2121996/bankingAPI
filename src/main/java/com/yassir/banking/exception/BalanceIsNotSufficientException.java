package com.yassir.banking.exception;

public class BalanceIsNotSufficientException extends Exception {

    public BalanceIsNotSufficientException(String errorMsg) {
        super(errorMsg);
    }

}