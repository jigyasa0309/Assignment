package com.demo.service;

import com.demo.dto.TaskRequest;
import com.demo.entity.Project;
import com.demo.entity.Task;
import com.demo.entity.TaskStatus;
import com.demo.repository.ProjectRepository;
import com.demo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

    public Task addTask(Long projectId, TaskRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        task.setProject(project);

        return taskRepository.save(task);
    }

    public List<Task> getTasks(Long projectId, TaskStatus status, String sortBy) {
        Sort sort = Sort.by(sortBy).ascending();
        if (status != null) {
            return taskRepository.findByProjectIdAndStatus(projectId, status, sort);
        }
        return taskRepository.findByProjectId(projectId, sort);
    }

    public Task updateTask(Long taskId, TaskRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
