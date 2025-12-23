package com.demo.service;

import com.demo.dto.ProjectRequest;
import com.demo.dto.ProjectResponse;
import com.demo.entity.Project;
import com.demo.entity.User;
import com.demo.repository.ProjectRepository;
import com.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    // ✅ CREATE PROJECT → RETURN DTO
    public ProjectResponse createProject(Long userId, ProjectRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setUser(user);
        project.setCreatedAt(LocalDateTime.now());

        Project saved = projectRepository.save(project);

        return mapToResponse(saved);
    }

    public List<ProjectResponse> getAllProjects(Long userId) {

        List<Project> projects = projectRepository.findByUserId(userId);

        return projects.stream()
                .map(this::mapToResponse)
                .toList();
    }

    public ProjectResponse updateProject(Long projectId, ProjectRequest request) {

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(request.getName());
        project.setDescription(request.getDescription());

        Project updated = projectRepository.save(project);

        return mapToResponse(updated);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    private ProjectResponse mapToResponse(Project p) {
        return new ProjectResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getCreatedAt(),
                p.getUpdatedAt()
        );
    }
}
