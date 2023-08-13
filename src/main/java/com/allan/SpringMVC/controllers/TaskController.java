package com.allan.SpringMVC.controllers;

import com.allan.SpringMVC.models.Task;
import com.allan.SpringMVC.models.TaskDTO;
import com.allan.SpringMVC.services.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }

    @GetMapping("/home")
     public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("tasks",taskService.findAll());
        return modelAndView;
    }

    @GetMapping("taskform")
    public String form(){
        return "taskForm";
    }

    @PostMapping("/create")
    public String saveTask(TaskDTO taskDTO) {
        taskService.saveTask(taskDTO);
        return "redirect:home";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return "redirect:/home";
    }
}
