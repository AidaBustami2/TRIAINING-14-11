package com.example.demo.test.Services;

import com.example.demo.test.Models.Task;
import com.example.demo.test.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;

    public TaskService() {
    }

    public List<Task> findByStatusLessThanEqual(int i) {
        return taskRepository.findByStatusLessThanEqual(i);
    }

    public Task save(Task currentTask) {
        return taskRepository.save(currentTask);
    }
}
