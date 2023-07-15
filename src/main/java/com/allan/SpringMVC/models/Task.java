package com.allan.SpringMVC.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_table")
public class Task {

    public Task(String name, LocalDateTime taskDate) {
        this.name = name;
        this.taskDate = taskDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50,nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime taskDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDateTime taskDate) {
        this.taskDate = taskDate;
    }
}
