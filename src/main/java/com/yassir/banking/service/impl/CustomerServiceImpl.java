package com.yassir.banking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yassir.banking.dto.CustomerDto;
import com.yassir.banking.entity.Customer;
import com.yassir.banking.mapper.CustomerMapper;
import com.yassir.banking.repository.CustomerRepository;
import com.yassir.banking.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private CustomerRepository customerRepository;
	private CustomerMapper customerMapper;

	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
		this.customerRepository = customerRepository;
		this.customerMapper = customerMapper;
	}

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer customer = customerMapper.fromDtoToEntity(customerDto);
		Customer savedCustomer = customerRepository.save(customer);
		return customerMapper.fromEntityToDto(savedCustomer);
	}

}
