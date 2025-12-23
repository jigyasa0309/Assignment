package com.demo.controller;

import com.demo.dto.ProjectRequest;
import com.demo.dto.ProjectResponse;
import com.demo.entity.Project;
import com.demo.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping("/add/{userId}")
    public ProjectResponse createProject(
            @PathVariable Long userId,
            @RequestBody ProjectRequest request) {

        return projectService.createProject(userId, request);
    }

    @GetMapping("/user/{userId}")
    public List<ProjectResponse> getProjects(@PathVariable Long userId) {
        return projectService.getAllProjects(userId);
    }

    @PutMapping("/update/{projectId}")
    public ProjectResponse updateProject(
            @PathVariable Long projectId,
            @RequestBody ProjectRequest request) {

        return projectService.updateProject(projectId, request);
    }

    @DeleteMapping("/delete/{projectId}")
    public String deleteProject(@PathVariable Long projectId) {
        projectService.deleteProject(projectId);
        return "Project deleted successfully";
    }
}
