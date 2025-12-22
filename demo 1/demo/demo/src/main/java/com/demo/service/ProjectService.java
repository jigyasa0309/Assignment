package com.demo.service;

import com.demo.dto.ProjectRequest;
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


    public Project createProject(Long userId, ProjectRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setUser(user);
        project.setCreatedAt(LocalDateTime.now());

        return projectRepository.save(project);
    }

    public List<Project> getAllProjects(Long userId) {
        return projectRepository.findAll();
    }

    public Project updateProject(Long projectId, ProjectRequest request) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(request.getName());
        project.setDescription(request.getDescription());

        return projectRepository.save(project);
    }


    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }
}
