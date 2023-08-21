package com.allan.SpringMVC.controllers;

import com.allan.SpringMVC.models.Entities.Task;
import com.allan.SpringMVC.models.DTOs.TaskDTO;
import com.allan.SpringMVC.models.Entities.User;
import com.allan.SpringMVC.services.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller()
@RequestMapping("task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService)
    {
        this.taskService = taskService;
    }

    @GetMapping("list")
     public ModelAndView list(@RequestParam(name = "currentpage",defaultValue = "1") int currentPage,@RequestParam(name = "size",defaultValue = "5")int sizePage){
        String username = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Pageable pageable = PageRequest.of(currentPage - 1,sizePage);
        Page<Task> taskPage = taskService.findAllTaskOfUser(username,pageable);

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("tasks",taskPage.getContent());

        int totalPages = taskPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            modelAndView.addObject("pageNumbers", pageNumbers);
            modelAndView.addObject("nextPage",1);
            modelAndView.addObject("previewPage",-1);
        }

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
        return "redirect:/task/list";
    }
}
