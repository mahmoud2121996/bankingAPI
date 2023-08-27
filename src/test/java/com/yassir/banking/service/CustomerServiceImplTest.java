package com.yassir.banking.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.yassir.banking.dto.CustomerDto;
import com.yassir.banking.entity.Account;
import com.yassir.banking.entity.Customer;
import com.yassir.banking.exception.CustomerNotFoundException;
import com.yassir.banking.mapper.CustomerMapper;
import com.yassir.banking.repository.CustomerRepository;
import com.yassir.banking.service.impl.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerServiceImpl;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerMapper customerMapper;

    private Customer customerEntity;
    private Optional<Customer> customer;
    private CustomerDto customerDto;

    @BeforeAll
    public void setup() {

        customerEntity = new Customer();
        customerEntity.setFullName("TEST Name");
        customerEntity.setId(1L);

        customer = Optional.of(customerEntity);

        customerDto = new CustomerDto(customerEntity.getId(), customerEntity.getFullName(),
                customerEntity.getCreationDate());

    }

    @Test
    public void testCreateCustomer() {

        // Given
        when(customerMapper.fromDtoToEntity(customerDto)).thenReturn(customerEntity);
        when(customerMapper.fromEntityToDto(customerEntity)).thenReturn(customerDto);
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);

        // When
        CustomerDto result = customerServiceImpl.createCustomer(customerDto);

        // Then
        assertEquals(result.getCreationDate(), customerDto.getCreationDate());
        assertEquals(result.getFullName(), customerDto.getFullName());
        assertEquals(result.getId(), customerDto.getId());

        Mockito.verify(customerRepository, times(1)).save(customerEntity);
        Mockito.verify(customerMapper, times(1)).fromDtoToEntity(customerDto);
        Mockito.verify(customerMapper, times(1)).fromEntityToDto(customerEntity);

    }

}
