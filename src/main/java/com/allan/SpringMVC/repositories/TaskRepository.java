package com.allan.SpringMVC.repositories;

import com.allan.SpringMVC.models.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
