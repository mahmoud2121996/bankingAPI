package com.yassir.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import com.yassir.banking.dto.AccountDto;
import com.yassir.banking.dto.BalanceDto;
import com.yassir.banking.entity.Account;
import com.yassir.banking.entity.Customer;
import com.yassir.banking.exception.AccountNotFoundException;
import com.yassir.banking.exception.CustomerNotFoundException;
import com.yassir.banking.mapper.AccountMapper;
import com.yassir.banking.repository.AccountRepository;
import com.yassir.banking.repository.CustomerRepository;
import com.yassir.banking.service.impl.AccountServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AccountMapper accountMapper;

    private AccountDto accountDto;
    private Customer customerEntity;
    private Optional<Customer> customer;
    private Optional<Account> account;
    private Account accountEntity;

    @BeforeAll
    public void setup() {
        accountDto = new AccountDto();
        accountDto.setAccountId(1L);
        accountDto.setBalance(1000.0);
        accountDto.setCustomerId(1L);

        customerEntity = new Customer();
        customerEntity.setFullName("TEST Name");
        customerEntity.setId(1L);

        customer = Optional.of(customerEntity);

        accountEntity = new Account();
        accountEntity.setBalance(accountDto.getBalance());
        accountEntity.setId(accountDto.getAccountId());
        accountEntity.setCustomer(customerEntity);

        account = Optional.of(accountEntity);
    }

    @Test
    public void testCreateAccount() throws CustomerNotFoundException {

        // Given
        when(customerRepository.findById(customerEntity.getId())).thenReturn(customer);
        when(accountRepository.save(accountEntity)).thenReturn(accountEntity);
        lenient().when(accountMapper.fromDtoToEntity(accountDto)).thenReturn(accountEntity);
        lenient().when(accountMapper.fromEntityToDto(accountEntity)).thenReturn(accountDto);

        // When
        AccountDto result = accountServiceImpl.createAccount(accountDto);

        // Then
        assertEquals(result.getAccountId(), accountDto.getAccountId());
        assertEquals(result.getBalance(), accountDto.getBalance());
        assertEquals(result.getCustomerId(), accountDto.getCustomerId());
        assertEquals(result.getCreationDate(), accountDto.getCreationDate());

        Mockito.verify(customerRepository, times(1)).findById(customerEntity.getId());
        Mockito.verify(accountRepository, times(1)).save(accountEntity);
        Mockito.verify(accountMapper, times(1)).fromDtoToEntity(accountDto);
        Mockito.verify(accountMapper, times(1)).fromEntityToDto(accountEntity);

    }

    @Test
    public void testRetriveBalance() throws AccountNotFoundException {

        // Given
        when(accountRepository.findById(1L)).thenReturn(account);

        // When
        BalanceDto result = accountServiceImpl.retriveBalance(accountEntity.getId());

        // Then
        assertEquals(result.getAccountId(), accountEntity.getId());
        assertEquals(result.getBalance(), accountEntity.getBalance());

        Mockito.verify(accountRepository, times(1)).findById(1L);
    }

    @Test
    public void testAccountNotFoundWhenRetriveBalance() throws AccountNotFoundException {

        when(accountRepository.findById(10L)).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountServiceImpl.retriveBalance(10L));

        Mockito.verify(accountRepository, times(1)).findById(10L);
    }

    @Test
    public void testCustomerNotFoundWhenCreateAccount() throws CustomerNotFoundException {

        when(customerRepository.findById(accountEntity.getId())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> accountServiceImpl.createAccount(accountDto));

        Mockito.verify(accountMapper, times(0)).fromDtoToEntity(Mockito.any(AccountDto.class));
        Mockito.verify(accountMapper, times(0)).fromEntityToDto(Mockito.any(Account.class));
        Mockito.verify(accountRepository, times(0)).save(Mockito.any(Account.class));
        Mockito.verify(customerRepository, times(1)).findById(accountEntity.getId());

    }
}
