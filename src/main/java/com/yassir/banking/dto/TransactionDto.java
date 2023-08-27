package com.yassir.banking.dto;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDto {

	private Long transactionId;

	@Min(1)
	private Long reciverId;

	@Min(1)
	private Long senderId;

	private Date transactionDate;

	@Min(1)
	private Double amount;

}
