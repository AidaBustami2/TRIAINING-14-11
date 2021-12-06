package com.example.demo.test.Services;
import com.example.demo.test.Models.ElementNotFoundException;
import com.example.demo.test.Models.Machine;
import com.example.demo.test.Models.Task;
import com.example.demo.test.Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService {
    @Autowired
    TaskService taskservice;
    @Autowired
    WorkerService workerservice;
    @Autowired
    MachineService machineservice;
    @Autowired
    UserService userService;
    @Autowired
    ThreadService threadService;

    public JobService() {
    }

    @KafkaListener(topics = "topic")
    public void consumeFromTopic(String task) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Task task1 = mapper.readValue(task, Task.class);
            taskservice.save(task1);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    public Machine addTaskToMachine(Task task, Machine machine) {
        Machine result = machineservice.addTaskToMachine(task, machine);
        if (result != null) {
            workerservice.work(task);
            return result;
        } else {
            return null;
        }
    }

    public Optional<User> findUser(Long userId) {
        return userService.findUser(userId);
    }

    public void addTaskByUser(User user, Task task) {
        userService.addTaskByUser(user, task);
    }

    public Optional<Machine> findMachine(Long machineId) {
        return machineservice.findMachine(machineId);
    }

    public void addMachineByUser(Machine machine, User user) {
        userService.addMachineByUser(machine, user);
    }

    public Task saveTask(Task task) {
        if (threadService.count() == 0)
            threadService.intilaize();
        return taskservice.save(task);
    }
    public Machine addTask(Task task1, Long userId, Long machineId) {
        Task task = saveTask(task1);
        Optional<User> optionalUser = findUser(userId);
        if (optionalUser.isPresent()) {
           Optional<Machine> optionalMachine = findMachine(machineId);
            if (optionalMachine.isPresent()) {
                addTaskByUser(optionalUser.get(), task);
                addMachineByUser(optionalMachine.get(), optionalUser.get());
                return addTaskToMachine(task, optionalMachine.get());
           } else { //"machine does not exist";
                throw new ElementNotFoundException("machine does not exist");
            }
        } else { //"machine does not exist";
            throw new ElementNotFoundException("user does not exist");
        }
    }
}