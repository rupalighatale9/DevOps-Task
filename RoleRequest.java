package com.usermanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RoleRequest {

    @NotBlank(message = "Role name is required")
    @Size(max = 50, message = "Role name must not exceed 50 characters")
    @Pattern(regexp = "^ROLE_[A-Z_]+$", message = "Role name must start with ROLE_ and contain only uppercase letters and underscores")
    private String name;

    public RoleRequest() {
    }

    public RoleRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
