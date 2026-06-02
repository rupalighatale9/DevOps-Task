package com.usermanagement.dto;

import java.util.Set;

public class LoginResponse {

    private String token;
    private String type = "Bearer";
    private Set<String> roles;

    public LoginResponse() {
    }

    public LoginResponse(String token, String type, Set<String> roles) {
        this.token = token;
        this.type = type;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
