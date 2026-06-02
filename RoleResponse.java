package com.usermanagement.dto;

public class RoleResponse {

    private Long id;
    private String name;

    public RoleResponse() {
    }

    public RoleResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static RoleResponse fromEntity(com.usermanagement.entity.Role role) {
        RoleResponse response = new RoleResponse();
        response.setId(role.getId());
        response.setName(role.getName());
        return response;
    }
}
