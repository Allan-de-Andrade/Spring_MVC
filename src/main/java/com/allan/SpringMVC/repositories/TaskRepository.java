package com.allan.SpringMVC.repositories;

import com.allan.SpringMVC.models.Entities.Task;
import com.allan.SpringMVC.models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findByTaskOwner(String taskOwner);
}
