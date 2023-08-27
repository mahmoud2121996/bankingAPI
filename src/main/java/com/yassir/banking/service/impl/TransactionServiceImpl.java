package com.yassir.banking.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yassir.banking.dto.TransactionDto;
import com.yassir.banking.entity.Transaction;
import com.yassir.banking.exception.AccountNotFoundException;
import com.yassir.banking.exception.BalanceIsNotSufficientException;
import com.yassir.banking.mapper.TransactionMapper;
import com.yassir.banking.repository.TransactionRepository;
import com.yassir.banking.service.AccountService;
import com.yassir.banking.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionMapper transactionMapper;
    private AccountService accountService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
            AccountService accountService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.accountService = accountService;
    }

    @Override
    @Transactional
    public TransactionDto createTransaction(TransactionDto transactionDto)
            throws BalanceIsNotSufficientException, AccountNotFoundException {

        accountService.deductFromAccountBalance(transactionDto.getSenderId(), transactionDto.getAmount());
        accountService.depositIntoAccountBalance(transactionDto.getReciverId(), transactionDto.getAmount());

        Transaction mappedTransaction = transactionMapper.fromDtoToEntity(transactionDto);

        Transaction savedTransaction = transactionRepository.save(mappedTransaction);

        return transactionMapper.fromEntityToDto(savedTransaction);
    }

    @Override
    public List<TransactionDto> retriveTransactionHistory(Long accountId) {
        List<Transaction> listOfTransactions = transactionRepository.findTransactionsByAccountId(accountId);
        return transactionMapper.fromEntityListToDtoList(listOfTransactions);
    }

}
