package com.usermanagement.service;

import com.usermanagement.dto.RoleRequest;
import com.usermanagement.dto.RoleResponse;

import java.util.List;

public interface RoleService {

    RoleResponse createRole(RoleRequest roleRequest);

    RoleResponse getRoleById(Long id);

    List<RoleResponse> getAllRoles();

    void deleteRole(Long id);

    void assignRoleToUser(Long userId, Long roleId);

    void removeRoleFromUser(Long userId, Long roleId);
}
