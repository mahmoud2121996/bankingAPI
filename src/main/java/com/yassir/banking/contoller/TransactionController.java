package com.yassir.banking.contoller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yassir.banking.dto.TransactionDto;
import com.yassir.banking.exception.AccountNotFoundException;
import com.yassir.banking.exception.BalanceIsNotSufficientException;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@RequestMapping("/api/v1/transaction")
@Validated
public interface TransactionController {

    @PostMapping
    @Operation(summary = "create a new transaction")
    public ResponseEntity<TransactionDto> createTransaction(@RequestBody @Valid TransactionDto transaction)
            throws BalanceIsNotSufficientException, AccountNotFoundException;

    @GetMapping("{accountId}")
    @Operation(summary = "get transaction history for a given account id")
    public ResponseEntity<List<TransactionDto>> getTransactionHistory(
            @PathVariable("accountId") @Min(0) Long accountId);
}
