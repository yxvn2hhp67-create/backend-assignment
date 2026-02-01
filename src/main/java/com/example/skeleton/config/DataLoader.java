package com.example.skeleton.config;

import com.example.skeleton.user.User;
import com.example.skeleton.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedUsers(UserRepository userRepo) {
        return args -> {
            if (userRepo.count() == 0) {
                userRepo.save(new User("John Example"));
            }
        };
    }
}
