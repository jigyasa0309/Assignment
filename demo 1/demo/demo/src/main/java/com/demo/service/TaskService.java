package com.demo.service;

import com.demo.dto.TaskRequest;
import com.demo.dto.TaskResponse;
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

    public TaskResponse addTask(Long projectId, TaskRequest request) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());
        task.setProject(project);

        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    public List<TaskResponse> getTasks(Long projectId, TaskStatus status, String sortBy) {

        Sort sort = Sort.by(sortBy).ascending();

        List<Task> tasks = (status != null)
                ? taskRepository.findByProjectIdAndStatus(projectId, status, sort)
                : taskRepository.findByProjectId(projectId, sort);

        return tasks.stream()
                .map(this::mapToResponse)
                .toList();
    }


    public TaskResponse updateTask(Long taskId, TaskRequest request) {

        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private TaskResponse mapToResponse(Task t) {
        return new TaskResponse(
                t.getId(),
                t.getTitle(),
                t.getDescription(),
                t.getStatus(),
                t.getDueDate(),
                t.getCreatedAt(),
                t.getUpdatedAt()
        );
    }
}
