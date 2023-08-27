package com.yassir.banking.contoller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.yassir.banking.contoller.TransactionController;
import com.yassir.banking.dto.TransactionDto;
import com.yassir.banking.exception.AccountNotFoundException;
import com.yassir.banking.exception.BalanceIsNotSufficientException;
import com.yassir.banking.service.TransactionService;

@RestController
public class TransactionControllerImpl implements TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionControllerImpl(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @Override
    public ResponseEntity<TransactionDto> createTransaction(TransactionDto transaction)
            throws BalanceIsNotSufficientException, AccountNotFoundException {
        TransactionDto savedTransaction = transactionService.createTransaction(transaction);
        return new ResponseEntity<TransactionDto>(savedTransaction, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<TransactionDto>> getTransactionHistory(Long accountId) {
        List<TransactionDto> listOfTransactions = transactionService.retriveTransactionHistory(accountId);
        return new ResponseEntity<List<TransactionDto>>(listOfTransactions, HttpStatus.OK);
    }

}
