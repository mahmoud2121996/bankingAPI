package com.yassir.banking.contoller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yassir.banking.dto.AccountDto;
import com.yassir.banking.dto.BalanceDto;
import com.yassir.banking.exception.AccountNotFoundException;
import com.yassir.banking.exception.CustomerNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/account")
@Validated
public interface AccountController {

        @PostMapping
        @Operation(summary = "create a new Account for customer")
        public ResponseEntity<AccountDto> createAccount(@RequestBody @Valid AccountDto account)
                        throws CustomerNotFoundException;

        @GetMapping("{accountId}")
        @Operation(summary = "retrive balance information for a given account id")
        public ResponseEntity<BalanceDto> retriveBalance(@PathVariable("accountId") @Min(0) Long accountId)
                        throws AccountNotFoundException;

        @PatchMapping
        @Operation(summary = "create a new deposit")
        public ResponseEntity<BalanceDto> updateAccountBalance(@RequestBody @Valid BalanceDto balanceDto)
                        throws AccountNotFoundException;
}
