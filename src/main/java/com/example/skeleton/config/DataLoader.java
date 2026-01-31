package com.example.skeleton.config;

import com.example.skeleton.task.Task;
import com.example.skeleton.task.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedTasks(TaskRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Task("Review requirements", false));
                repo.save(new Task("Design API", true));
                repo.save(new Task("Implement frontend", false));
            }
        };
    }
}
