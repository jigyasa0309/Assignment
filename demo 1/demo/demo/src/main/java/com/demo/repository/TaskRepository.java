package com.demo.repository;


import com.demo.entity.Task;
import com.demo.entity.TaskStatus;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId, Sort sort);

    List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status, Sort sort);
}
