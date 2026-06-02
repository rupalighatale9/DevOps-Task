package com.usermanagement.controller;

import com.usermanagement.dto.TaskResponse;
import com.usermanagement.dto.UserResponse;
import com.usermanagement.security.userdetails.CustomUserDetails;
import com.usermanagement.service.TaskService;
import com.usermanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "User APIs", description = "User profile and task APIs - Requires authentication")
public class UserController {

    private final UserService userService;
    private final TaskService taskService;

    public UserController(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/profile")
    @Operation(summary = "Get user profile", description = "Get current user's profile")
    public ResponseEntity<UserResponse> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        UserResponse userResponse = userService.getUserById(userDetails.getId());
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/tasks")
    @Operation(summary = "Get user's tasks", description = "Get tasks assigned to current user")
    public ResponseEntity<List<TaskResponse>> getUserTasks(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<TaskResponse> tasks = taskService.getTasksByAssignedUser(userDetails.getId());
        return ResponseEntity.ok(tasks);
    }
}
