package com.example.demo.test.Services;

import com.example.demo.test.Models.Machine;
import com.example.demo.test.Models.Task;
import com.example.demo.test.Models.User;
import com.example.demo.test.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserService() {
    }

    public void addTaskByUser(User user, Task task) {
        user.fetchTaskByUser().add(task);
        userRepository.save(user);
    }

    public void addMachineByUser(Machine machine, User user) {
        user.fetchMachineByUser().add(machine);
        userRepository.save(user);
    }

    public Optional<User> findUser(Long userId) {
        return userRepository.findById(userId);
    }

    public void save(User user) {
        userRepository.save(user);
    }
}
