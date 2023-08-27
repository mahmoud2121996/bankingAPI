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
@Entity(name = "account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence_generator")
	@SequenceGenerator(name = "account_sequence_generator", allocationSize = 1, initialValue = 1)
	@Column
	private Long id;
	
	@Column
	private Double balance;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Customer customer;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@PrePersist
	private void onCreate() {
		this.creationDate = new Timestamp(System.currentTimeMillis());
	}

}
