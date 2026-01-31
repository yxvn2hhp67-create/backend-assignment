package com.example.skeleton.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

    // Query method where Spring interprets this based off method name and params.
    // Checks to see if User already has an account with the given name.
    boolean existsByAccountNameAndUser_Id(String accountName, Long userId);
}
