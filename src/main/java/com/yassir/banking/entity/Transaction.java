package com.yassir.banking.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "transaction")
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence_generator")
	@SequenceGenerator(name = "transaction_sequence_generator", allocationSize = 1, initialValue = 1)
	@Column
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date transactionDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer sender;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer reciver;
	
	@Column
	private Double amount;

	@PrePersist
	private void onCreate() {
		this.transactionDate = new Timestamp(System.currentTimeMillis());
	}

}
