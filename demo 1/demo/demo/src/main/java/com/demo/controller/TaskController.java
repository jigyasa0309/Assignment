package com.demo.controller;

import com.demo.dto.TaskRequest;
import com.demo.entity.Task;
import com.demo.entity.TaskStatus;
import com.demo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping("/api/projects/{projectId}/tasks")
    public Task addTask(
            @PathVariable Long projectId,
            @RequestBody TaskRequest request) {
        return taskService.addTask(projectId, request);
    }

    @GetMapping("/api/projects/{projectId}/tasks")
    public List<Task> getTasks(
            @PathVariable Long projectId,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(defaultValue = "createdAt") String sortBy) {

        return taskService.getTasks(projectId, status, sortBy);
    }

    @PutMapping("/api/tasks/{taskId}")
    public Task updateTask(
            @PathVariable Long taskId,
            @RequestBody TaskRequest request) {
        return taskService.updateTask(taskId, request);
    }

    @DeleteMapping("/api/tasks/{taskId}")
    public String deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return "Task deleted successfully";
    }
}
