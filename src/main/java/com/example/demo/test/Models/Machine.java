package com.example.demo.test.Models;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;
@Setter
@Entity
public class Machine {
    @NotEmpty
    String name;
    @NotEmpty
    String ip;
    @NotEmpty
    String location;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "machineId", nullable = false)
    private Long machineId;
    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "MachineAndTask",
            joinColumns = @JoinColumn(name = "machineId"),
            inverseJoinColumns = @JoinColumn(name = "taskId"))
    private Set<Task> allTasks = new HashSet<Task>();

    @ManyToMany(mappedBy = "machineByUser", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<User> userList = new HashSet<User>();

    public Machine() {
    }

    public Long getMachineId() {
        return machineId;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public String getLocation() {
        return location;
    }

    public Set<User> getUserList() {
        return userList;
    }

    public Set<Task> fetchAllTasks() {
        return allTasks;
    }
}
