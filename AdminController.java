package com.usermanagement.controller;

import com.usermanagement.dto.RoleRequest;
import com.usermanagement.dto.RoleResponse;
import com.usermanagement.dto.UserRequest;
import com.usermanagement.dto.UserResponse;
import com.usermanagement.dto.UserUpdateRequest;
import com.usermanagement.service.RoleService;
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
@RequestMapping("/api/admin")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Admin APIs", description = "Admin management APIs - Requires ROLE_ADMIN")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    // User Management Endpoints

    @PostMapping("/users")
    @Operation(summary = "Create a new user", description = "Create a new user (Admin only)")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.createUser(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping("/users/{id}")
    @Operation(summary = "Get user by ID", description = "Get a user by ID (Admin only)")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse userResponse = userService.getUserById(id);
        return ResponseEntity.ok(userResponse);
    }

    @GetMapping("/users")
    @Operation(summary = "Get all users", description = "Get all users (Admin only)")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<UserResponse> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/users/{id}")
    @Operation(summary = "Update user", description = "Update user details (Admin only)")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserUpdateRequest userUpdateRequest) {
        UserResponse userResponse = userService.updateUser(id, userUpdateRequest);
        return ResponseEntity.ok(userResponse);
    }

    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete user", description = "Delete a user (Admin only)")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Role Management Endpoints

    @PostMapping("/roles")
    @Operation(summary = "Create a new role", description = "Create a new role (Admin only)")
    public ResponseEntity<RoleResponse> createRole(@Valid @RequestBody RoleRequest roleRequest) {
        RoleResponse roleResponse = roleService.createRole(roleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(roleResponse);
    }

    @GetMapping("/roles/{id}")
    @Operation(summary = "Get role by ID", description = "Get a role by ID (Admin only)")
    public ResponseEntity<RoleResponse> getRoleById(@PathVariable Long id) {
        RoleResponse roleResponse = roleService.getRoleById(id);
        return ResponseEntity.ok(roleResponse);
    }

    @GetMapping("/roles")
    @Operation(summary = "Get all roles", description = "Get all roles (Admin only)")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        List<RoleResponse> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @DeleteMapping("/roles/{id}")
    @Operation(summary = "Delete role", description = "Delete a role (Admin only)")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/users/{userId}/roles/{roleId}")
    @Operation(summary = "Assign role to user", description = "Assign a role to a user (Admin only)")
    public ResponseEntity<Void> assignRoleToUser(@PathVariable Long userId, @PathVariable Long roleId) {
        roleService.assignRoleToUser(userId, roleId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{userId}/roles/{roleId}")
    @Operation(summary = "Remove role from user", description = "Remove a role from a user (Admin only)")
    public ResponseEntity<Void> removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId) {
        roleService.removeRoleFromUser(userId, roleId);
        return ResponseEntity.ok().build();
    }
}
