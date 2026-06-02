package com.usermanagement.service;

import com.usermanagement.dto.TaskRequest;
import com.usermanagement.dto.TaskResponse;

import java.util.List;

public interface TaskService {

    TaskResponse createTask(TaskRequest taskRequest);

    TaskResponse getTaskById(Long id);

    List<TaskResponse> getAllTasks();

    List<TaskResponse> getTasksByAssignedUser(Long userId);

    TaskResponse updateTask(Long id, TaskRequest taskRequest);

    TaskResponse updateTaskStatus(Long id, com.usermanagement.entity.Task.TaskStatus status);

    void deleteTask(Long id);

    TaskResponse assignTaskToUser(Long taskId, Long userId);
}
