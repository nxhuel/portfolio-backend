package com.portfolio_personal.backend.service;

import com.portfolio_personal.backend.persistence.entity.PermissionEntity;

import java.util.List;
import java.util.Optional;

public interface IPermissionService {
    List<PermissionEntity> getPermissionList();
    Optional<PermissionEntity> getPermission(Long id);
    PermissionEntity createPermission(PermissionEntity permission);
    PermissionEntity updatePermission(Long id, PermissionEntity updatedPermission);
    void deletePermission(Long id);
}
