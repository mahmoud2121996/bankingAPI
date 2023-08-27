package com.yassir.banking.exception;

public class AccountNotFoundException extends Exception {

    public AccountNotFoundException(String errorMsg) {
        super(errorMsg);
    }

}
