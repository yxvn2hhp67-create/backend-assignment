package com.example.skeleton.transaction;

import com.example.skeleton.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Query method where Spring interprets this based off method name and params.
    // Returns all Accounts for the given UserId
    List<Transaction> findByAccount_Id(Long accountId);
}
