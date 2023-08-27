package com.yassir.banking.service;

import java.util.List;

import com.yassir.banking.dto.TransactionDto;
import com.yassir.banking.exception.AccountNotFoundException;
import com.yassir.banking.exception.BalanceIsNotSufficientException;

public interface TransactionService {
    public TransactionDto createTransaction(TransactionDto transactionDto)
            throws BalanceIsNotSufficientException, AccountNotFoundException;

    public List<TransactionDto> retriveTransactionHistory(Long accountId);
}
