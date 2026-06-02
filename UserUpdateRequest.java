package com.usermanagement.dto;

import jakarta.validation.constraints.Size;

public class UserUpdateRequest {

    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;

    private Boolean enabled;

    public UserUpdateRequest() {
    }

    public UserUpdateRequest(String name, Boolean enabled) {
        this.name = name;
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
