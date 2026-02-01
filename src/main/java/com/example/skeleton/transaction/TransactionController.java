package com.example.skeleton.transaction;

import com.example.skeleton.account.Account;
import com.example.skeleton.account.AccountRepository;
import com.example.skeleton.exceptions.AccountNotFoundException;
import com.example.skeleton.exceptions.InvalidTransactionRequestException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionController(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/{accountId}")
    public List<Transaction> all(@PathVariable("accountId") Long accountId) {
        return transactionRepository.findByAccount_Id(accountId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@Valid @RequestBody CreateTransactionRequest req) {

        if(req.accountId == null || req.accountId <= 0){
            throw new InvalidTransactionRequestException("Bad Request", "AccountId is a required field and must be greater than 0.");
        }

        String enumType;
        try {
            enumType = req.type.toUpperCase();
            Transaction.TransactionType.valueOf(enumType);
        } catch (IllegalArgumentException ex) {
            throw new InvalidTransactionRequestException("Bad Request", "TransactionType must be 'expense' or 'income'");
        }

        BigDecimal amount = new BigDecimal("0.0");
        if(req.amount != null) {
            amount = req.amount;
        }

        Date date = new Date();
        if(req.date != null) {
            date = req.date;
        }

        Account account = accountRepository.findById(req.accountId())
                .orElseThrow(() -> new AccountNotFoundException(req.accountId()));

        Transaction transaction = new Transaction(account, date, req.description(), amount, enumType);
        return transactionRepository.save(transaction);
    }

    public record CreateTransactionRequest(BigDecimal amount, Date date, String description, String type, Long accountId) {
    }
}
