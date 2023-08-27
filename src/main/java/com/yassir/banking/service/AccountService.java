package com.yassir.banking.service;

import com.yassir.banking.dto.AccountDto;
import com.yassir.banking.dto.BalanceDto;
import com.yassir.banking.entity.Account;
import com.yassir.banking.exception.AccountNotFoundException;
import com.yassir.banking.exception.BalanceIsNotSufficientException;
import com.yassir.banking.exception.CustomerNotFoundException;

public interface AccountService {
    public AccountDto createAccount(AccountDto account) throws CustomerNotFoundException;

    public BalanceDto retriveBalance(Long accountId) throws AccountNotFoundException;

    public Account retriveAccount(Long accountId) throws AccountNotFoundException;

    public BalanceDto depositIntoAccountBalance(Long accountId, Double amount) throws AccountNotFoundException;

    public BalanceDto deductFromAccountBalance(Long accountId, Double amount)
            throws BalanceIsNotSufficientException, AccountNotFoundException;
}
