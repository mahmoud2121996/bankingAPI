package com.yassir.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yassir.banking.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
