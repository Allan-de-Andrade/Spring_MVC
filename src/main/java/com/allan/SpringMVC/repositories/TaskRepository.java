package com.allan.SpringMVC.repositories;

import com.allan.SpringMVC.models.Entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task,Long> {
    Page<Task> findByTaskOwnerAndTaskDeleted(String taskOwner, Boolean taskDeleted, Pageable pageable);
}
