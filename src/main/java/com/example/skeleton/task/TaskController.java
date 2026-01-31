package com.example.skeleton.task;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Task> all() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Task byId(@PathVariable("id") Long id) {
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody CreateTaskRequest req) {
        Task t = new Task(req.title(), req.completed());
        return repository.save(t);
    }

    @PutMapping("/{id}")
    public Task update(@PathVariable("id") Long id, @Valid @RequestBody UpdateTaskRequest req) {
        Task existing = repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
        existing.setTitle(req.title());
        existing.setCompleted(req.completed());
        return repository.save(existing);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
    }

    public record CreateTaskRequest(@NotBlank String title, boolean completed) {
    }

    public record UpdateTaskRequest(@NotBlank String title, boolean completed) {
    }
}
