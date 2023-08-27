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
public class AccountDto {

	private Long accountId;

	@Min(1)
	private Long customerId;

	private Date creationDate;

	@Min(1)
	private Double balance;

}
