package com.yassir.banking.contoller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.yassir.banking.contoller.CustomerController;
import com.yassir.banking.dto.CustomerDto;
import com.yassir.banking.service.CustomerService;

@RestController
public class CustomerControllerImpl implements CustomerController {

	private CustomerService customerService;

	@Autowired
	public CustomerControllerImpl(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Override
	public ResponseEntity<CustomerDto> createCustomer(CustomerDto customer) {
		CustomerDto createdCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<CustomerDto>(createdCustomer, HttpStatus.CREATED);
	}

}
