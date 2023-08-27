package com.yassir.banking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.yassir.banking.dto.ErrorResponseDto;

@ControllerAdvice
public class MainExceptionHandler {

    @ExceptionHandler(value = { CustomerNotFoundException.class, AccountNotFoundException.class })
    public ResponseEntity<ErrorResponseDto> notFoundExceptionsHandler(Exception ex) {
        return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto(ex.getLocalizedMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = BalanceIsNotSufficientException.class)
    public ResponseEntity<ErrorResponseDto> balanceIsNotSufficientExceptionHandler(Exception ex) {
        return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto(ex.getLocalizedMessage()),
                HttpStatus.BAD_REQUEST);
    }

}
