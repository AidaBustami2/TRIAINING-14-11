package com.example.demo.test.Controllers;

import com.example.demo.test.Models.ElementNotFoundException;
import com.example.demo.test.Models.Machine;
import com.example.demo.test.Models.Task;
import com.example.demo.test.Services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/Task")
public class TaskController {
    @Autowired
    JobService jobService;

    @PostMapping("/{userId}/{machineId}")
    public Machine addTask(@RequestBody Task task1, @PathVariable Long userId, @PathVariable Long machineId) {

        Machine machine = jobService.addTask(task1, userId, machineId);
        if (machine != null)
            return machine;
        else {  //  "user with this id does not exist";
            throw new ElementNotFoundException("user  dees not exsist , from sustom");
        }
    }
}