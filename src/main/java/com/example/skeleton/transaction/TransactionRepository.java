package com.example.skeleton.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Query method where Spring interprets this based off method name and params.
    // Returns all Transactions for the given accountId
    List<Transaction> findByAccount_Id(Long accountId);

    // Query the account table for the accounts initial balance, then add it to the sum of
    // each transaction amount. Transaction amount subtracted for expense entries, and added for income entries.
    @Query(value = "SELECT (SELECT initial_balance FROM app_account WHERE id = :accountId) + COALESCE((SELECT SUM(CASE WHEN UPPER(type) = 'EXPENSE' THEN -amount ELSE amount END) FROM app_transactions WHERE account_id = :accountId), 0)", nativeQuery = true)
    BigDecimal sumAmountByAccountId(@Param("accountId") Long accountId);
}
