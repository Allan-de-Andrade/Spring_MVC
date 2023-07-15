package com.allan.SpringMVC.controllers;

import com.allan.SpringMVC.models.Task;
import com.allan.SpringMVC.services.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.ldap.PagedResultsControl;
import java.util.List;
import java.util.Optional;

@Controller
public class FormController {

    private final TaskService  taskService;

    public FormController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/form")
    public String form(Model model){
        List<Task> tasks = taskService.findAll();
        model.addAttribute("tasks",tasks);
        return "formTask";
    }
}
