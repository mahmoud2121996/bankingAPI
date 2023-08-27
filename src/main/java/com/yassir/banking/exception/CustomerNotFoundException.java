package com.yassir.banking.exception;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException(String errorMsg) {
        super(errorMsg);
    }

}
