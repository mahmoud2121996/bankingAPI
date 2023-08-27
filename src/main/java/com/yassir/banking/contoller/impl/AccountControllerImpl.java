package com.yassir.banking.contoller.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.yassir.banking.contoller.AccountController;
import com.yassir.banking.dto.AccountDto;
import com.yassir.banking.dto.BalanceDto;
import com.yassir.banking.exception.AccountNotFoundException;
import com.yassir.banking.exception.CustomerNotFoundException;
import com.yassir.banking.service.AccountService;

import jakarta.validation.Valid;

@RestController
public class AccountControllerImpl implements AccountController {
    private AccountService accountService;

    public AccountControllerImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public ResponseEntity<AccountDto> createAccount(@Valid AccountDto account) throws CustomerNotFoundException {
        AccountDto createdAccount = accountService.createAccount(account);
        return new ResponseEntity<AccountDto>(createdAccount, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BalanceDto> retriveBalance(Long accountId) throws AccountNotFoundException {
        BalanceDto balanceDto = accountService.retriveBalance(accountId);
        return new ResponseEntity<BalanceDto>(balanceDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BalanceDto> updateAccountBalance(@Valid BalanceDto balanceDto)
            throws AccountNotFoundException {
        BalanceDto updatedBalanceDto = accountService.depositIntoAccountBalance(balanceDto.getAccountId(),
                balanceDto.getBalance());
        return new ResponseEntity<BalanceDto>(updatedBalanceDto, HttpStatus.OK);
    }

}
