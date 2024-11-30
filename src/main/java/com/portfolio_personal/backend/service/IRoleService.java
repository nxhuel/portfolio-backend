package com.portfolio_personal.backend.service;

import com.portfolio_personal.backend.persistence.entity.RoleEntity;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    List<RoleEntity> getRoleList();
    RoleEntity getRole(Long id);
    RoleEntity createRole(RoleEntity role);
    RoleEntity updateRole(Long id, RoleEntity updatedRole);
    void deleteRole(Long id);
}
