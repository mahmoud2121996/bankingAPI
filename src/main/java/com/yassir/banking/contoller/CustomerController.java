package com.yassir.banking.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yassir.banking.dto.CustomerDto;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/customer")
@Validated
public interface CustomerController {

	@PostMapping
	@Operation(summary = "create a new customer")
	public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CustomerDto customer);

}
