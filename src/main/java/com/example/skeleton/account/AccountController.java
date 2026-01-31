package com.example.skeleton.account;

import com.example.skeleton.exceptions.InvalidAccountRequestException;
import com.example.skeleton.user.User;
import com.example.skeleton.exceptions.UserNotFoundException;
import com.example.skeleton.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    public AccountController(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@Valid @RequestBody CreateAccountRequest req) {

        if(req.userId == null || req.userId <= 0){
            throw new InvalidAccountRequestException("Bad Request", "UserID is a required field and must be greater than 0.");
        }

        User user = userRepository.findById(req.userId())
                .orElseThrow(() -> new UserNotFoundException(req.userId()));

        if(accountRepository.existsByAccountNameAndUser_Id(req.accountName, user.getId())) {
            throw new InvalidAccountRequestException("Bad Request", "Account name " + req.accountName + " already exists for user.");
        }

        BigDecimal initialBalance = new BigDecimal("0.0");
        if(req.initialBalance != null) {
            initialBalance = req.initialBalance;
        }

        Account account = new Account(req.accountName(), initialBalance, user);
        return accountRepository.save(account);
    }

    public record CreateAccountRequest(Long userId, String accountName, BigDecimal initialBalance) {
    }
}
