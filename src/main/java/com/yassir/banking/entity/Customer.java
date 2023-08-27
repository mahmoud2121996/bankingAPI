package com.yassir.banking.entity;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Entity(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_sequence_generator")
	@SequenceGenerator(name = "customer_sequence_generator", allocationSize = 1, initialValue = 1)
	@Column
	private Long id;

	@Column
	private String fullName;

	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;

	@PrePersist
	private void onCreate() {
		this.creationDate = new Timestamp(System.currentTimeMillis());
	}

}
