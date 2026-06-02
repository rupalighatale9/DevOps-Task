package com.usermanagement.controller;

import com.usermanagement.dto.TaskRequest;
import com.usermanagement.dto.TaskResponse;
import com.usermanagement.dto.UserResponse;
import com.usermanagement.entity.Task;
import com.usermanagement.service.TaskService;
import com.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manager")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Manager APIs", description = "Manager management APIs - Requires ROLE_MANAGER")
@PreAuthorize("hasRole('MANAGER')")
public class ManagerController {

    private final TaskService taskService;
    private final UserService userService;

    public ManagerController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    // Task Management Endpoints

    @PostMapping("/tasks")
    @Operation(summary = "Create a new task", description = "Create a new task (Manager only)")
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.createTask(taskRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @GetMapping("/tasks/{id}")
    @Operation(summary = "Get task by ID", description = "Get a task by ID (Manager only)")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse taskResponse = taskService.getTaskById(id);
        return ResponseEntity.ok(taskResponse);
    }

    @GetMapping("/tasks")
    @Operation(summary = "Get all tasks", description = "Get all tasks (Manager only)")
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        List<TaskResponse> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/tasks/{id}")
    @Operation(summary = "Update task", description = "Update task details (Manager only)")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest taskRequest) {
        TaskResponse taskResponse = taskService.updateTask(id, taskRequest);
        return ResponseEntity.ok(taskResponse);
    }

    @PatchMapping("/tasks/{id}/status")
    @Operation(summary = "Update task status", description = "Update task status (Manager only)")
    public ResponseEntity<TaskResponse> updateTaskStatus(@PathVariable Long id, @RequestParam Task.TaskStatus status) {
        TaskResponse taskResponse = taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok(taskResponse);
    }

    @DeleteMapping("/tasks/{id}")
    @Operation(summary = "Delete task", description = "Delete a task (Manager only)")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/tasks/{taskId}/assign/{userId}")
    @Operation(summary = "Assign task to user", description = "Assign a task to a user (Manager only)")
    public ResponseEntity<TaskResponse> assignTaskToUser(@PathVariable Long taskId, @PathVariable Long userId) {
        TaskResponse taskResponse = taskService.assignTaskToUser(taskId, userId);
        return ResponseEntity.ok(taskResponse);
    }

    // User View Endpoints

    @GetMapping("/users")
    @Operation(summary = "View all users", description = "View all users (Manager only)")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
