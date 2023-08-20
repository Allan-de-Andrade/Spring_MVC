package com.allan.SpringMVC.services;

import com.allan.SpringMVC.models.Entities.Task;
import com.allan.SpringMVC.models.DTOs.TaskDTO;
import com.allan.SpringMVC.models.Entities.User;
import com.allan.SpringMVC.repositories.TaskRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    private  final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTaskOfUser(String taskOwner){
        return taskRepository.findByTaskOwner(taskOwner);
    }

    public Task getTaskById(Long id){
        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent())
            return task.get();
        else
            throw  new NullPointerException("This task not exist");
    }

    public Task saveTask(TaskDTO taskDTO){
        try {
            LocalDate taskDate = LocalDate.parse(taskDTO.getDate());

            Task task = new Task();
            task.setName(taskDTO.getName());
            task.setDate(taskDate);

            User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            task.setTaskOwner(currentUser.getUsername());

            return taskRepository.save(task);
        }

        catch (NullPointerException nu) {
            throw  new NullPointerException("This task don't save,Because not have datas enough for this");
        }
    }

    public Task editTask(TaskDTO taskDTO,Long id) {
        Optional<Task> task = taskRepository.findById(id);

        LocalDate taskDate = LocalDate.parse(taskDTO.getDate());

        if(task.isPresent()){
            task.get().setName(taskDTO.getName());
            task.get().setDate(taskDate);
        }

        else
            throw new NullPointerException("This task not exist");

        return taskRepository.save(task.get());
    }

    public Boolean deleteTask(Long id){
        try {
            taskRepository.deleteById(id);
            return Boolean.TRUE;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
}
