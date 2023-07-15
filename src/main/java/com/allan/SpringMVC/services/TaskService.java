package com.allan.SpringMVC.services;

import com.allan.SpringMVC.models.Task;
import com.allan.SpringMVC.repositories.TaskRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskService {

    private   TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
       return  taskRepository.findAll();
    }

}
