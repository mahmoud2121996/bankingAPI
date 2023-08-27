package com.yassir.banking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yassir.banking.dto.AccountDto;
import com.yassir.banking.dto.BalanceDto;
import com.yassir.banking.entity.Account;
import com.yassir.banking.entity.Customer;
import com.yassir.banking.exception.AccountNotFoundException;
import com.yassir.banking.exception.BalanceIsNotSufficientException;
import com.yassir.banking.exception.CustomerNotFoundException;
import com.yassir.banking.mapper.AccountMapper;
import com.yassir.banking.repository.AccountRepository;
import com.yassir.banking.repository.CustomerRepository;
import com.yassir.banking.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;
    private AccountMapper accountMapper;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository,
            AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public AccountDto createAccount(AccountDto account) throws CustomerNotFoundException {

        Long customerId = account.getCustomerId();

        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isEmpty()) {
            throw new CustomerNotFoundException("Customer Not Found...");
        }

        Account mappedAccount = accountMapper.fromDtoToEntity(account);
        Account savedAccount = accountRepository.save(mappedAccount);

        return accountMapper.fromEntityToDto(savedAccount);
    }

    @Override
    public BalanceDto retriveBalance(Long accountId) throws AccountNotFoundException {

        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            Account retreviedAccount = account.get();
            BalanceDto balanceDto = new BalanceDto(retreviedAccount.getId(), retreviedAccount.getBalance());
            return balanceDto;
        }
        throw new AccountNotFoundException("Account Not Found...");
    }

    @Override
    public Account retriveAccount(Long accountId) throws AccountNotFoundException {
        Optional<Account> account = accountRepository.findById(accountId);
        if (account.isPresent()) {
            return account.get();
        }

        throw new AccountNotFoundException("Account with Id " + accountId + " is Not Found");
    }

    @Override
    public BalanceDto depositIntoAccountBalance(Long accountId, Double amount) throws AccountNotFoundException {
        Account account = retriveAccount(accountId);
        Double newAccountBalance = account.getBalance() + amount;
        account.setBalance(newAccountBalance);
        accountRepository.save(account);
        BalanceDto balanceDto = new BalanceDto(accountId, newAccountBalance);
        return balanceDto;
    }

    @Override
    public BalanceDto deductFromAccountBalance(Long accountId, Double amount)
            throws BalanceIsNotSufficientException, AccountNotFoundException {
        Account account = retriveAccount(accountId);
        Double newAccountBalance;
        if (account.getBalance() >= amount) {
            newAccountBalance = account.getBalance() - amount;
            account.setBalance(newAccountBalance);
            accountRepository.save(account);
            BalanceDto balanceDto = new BalanceDto(accountId, newAccountBalance);
            return balanceDto;
        }
        throw new BalanceIsNotSufficientException(
                "Account number " + accountId + " does not have sufficient amount...");
    }

}
