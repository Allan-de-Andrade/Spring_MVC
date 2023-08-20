package com.allan.SpringMVC.controllers;

import com.allan.SpringMVC.models.Entities.Task;
import com.allan.SpringMVC.models.DTOs.TaskDTO;
import com.allan.SpringMVC.models.Entities.User;
import com.allan.SpringMVC.services.TaskService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller()
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }

    @GetMapping("list")
     public ModelAndView list(){
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("tasks",taskService.findAllTaskOfUser(username));
        return modelAndView;
    }

    @GetMapping("form")
    public String form(){
        return "taskForm";
    }

    @PostMapping("/create")
    public String saveTask(TaskDTO taskDTO) {
        taskService.saveTask(taskDTO);
        return "redirect:list";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editPage(@PathVariable Long id){
        Task taskEdit = taskService.getTaskById(id);

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(taskEdit.getId());
        taskDTO.setName(taskEdit.getName());
        taskDTO.setDate(taskEdit.getDate().toString());

        ModelAndView modelAndView = new ModelAndView("taskForm");
        modelAndView.addObject("task",taskDTO);
        return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String editTask(TaskDTO taskDTO,@PathVariable Long id){
        Task taskEdit = taskService.editTask(taskDTO,id);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id)
    {
        taskService.deleteTask(id);
        return "redirect:/list";
    }
}
