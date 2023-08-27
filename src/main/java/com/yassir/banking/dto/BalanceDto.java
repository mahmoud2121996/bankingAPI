package com.yassir.banking.dto;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class BalanceDto {
    @Min(0)
    private Long accountId;

    @Min(0)
    private Double balance;
}
