package com.demo.dto;


import com.demo.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {

    @NotBlank(message = "Task title is required")
    private String title;

    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    private LocalDate dueDate;
}
