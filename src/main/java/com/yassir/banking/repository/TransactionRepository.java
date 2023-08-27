package com.yassir.banking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.yassir.banking.entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Query("select t from transaction t where t.sender.id = ?1 or t.reciver.id = ?1")
    List<Transaction> findTransactionsByAccountId(Long accountId);

}
