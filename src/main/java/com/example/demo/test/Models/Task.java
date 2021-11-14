package com.example.demo.test.Models;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false)
    private Long taskId;
    int type;    //Start , Shutdown,Restart ,Configure ,	Status
    String Description;
    int Last_execution_time;
    int Number_of_trials;
    int Next_execution_time;
    int Priority;
    @NotEmpty
    String name;
    int status = 0; /*[Pending 0, In-Progress 1 , Completed 2 ,   aborted 3 and failed 4*/
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "threadId", referencedColumnName = "thread_id")
    Thread thread;
    @ManyToMany(mappedBy = "allTasks", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<Machine> machineList = new HashSet<Machine>();
    @ManyToMany(mappedBy = "taskByUser", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private Set<User> userList = new HashSet<User>();
    @CreatedDate
    @Temporal(TIMESTAMP)
    Date createdDate;
    @LastModifiedDate
    @Temporal(TIMESTAMP)
    Date modifiedDate;

    public Task() {
    }

    public void removeMachine(Machine machine) {
        machineList.remove(machine);
    }

    public Set<User> fetch() {
        return userList;
    }

    public Long getTaskId() {
        return taskId;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return Description;
    }

    public int getLast_execution_time() {
        return Last_execution_time;
    }

    public int getNumber_of_trials() {
        return Number_of_trials;
    }

    public int getNext_execution_time() {
        return Next_execution_time;
    }

    public int getPriority() {
        return Priority;
    }

    public String getName() {
        return name;
    }

    public int getStatus() {
        return status;
    }

    public Thread getThread() {
        return thread;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}