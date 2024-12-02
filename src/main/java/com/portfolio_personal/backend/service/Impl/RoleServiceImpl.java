package com.portfolio_personal.backend.service.Impl;

import com.portfolio_personal.backend.persistence.entity.PermissionEntity;
import com.portfolio_personal.backend.persistence.entity.RoleEntity;
import com.portfolio_personal.backend.persistence.repository.PermissionRepository;
import com.portfolio_personal.backend.persistence.repository.RoleRepository;
import com.portfolio_personal.backend.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<RoleEntity> getRoleList() {
        List<RoleEntity> roleList = roleRepository.findAll();
        if (roleList.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No  existen roles creados");
        }
        return roleList;
    }

    @Override
    public RoleEntity getRole(Long id) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Rol con ID " + id + " no encontrado"
                ));
        return role;
    }

    @Override
    public RoleEntity createRole(RoleEntity role) {
        Set<PermissionEntity> permissions = role.getPermissionList().stream()
                .map(permission -> permissionRepository.findById(permission.getId())
                        .orElseThrow(() -> new RuntimeException("Permission not found: " + permission.getId())))
                .collect(Collectors.toSet());
        role.setPermissionList(permissions);
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity updateRole(Long id, RoleEntity updatedRole) {
        return roleRepository.findById(id)
                .map(role -> {
                    role.setRoleEnum(updatedRole.getRoleEnum());
                    Set<PermissionEntity> permissions = role.getPermissionList().stream()
                            .map(permission -> permissionRepository.findById(permission.getId())
                                    .orElseThrow(() -> new RuntimeException("Permission not found: " + permission.getId())))
                            .collect(Collectors.toSet());
                    role.setPermissionList(permissions);
                    return roleRepository.save(role);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Rol con ID " + id + " no encontado"
                ));
    }

    @Override
    public void deleteRole(Long id) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Rol con ID " + id + " no encontrado"
                ));
        roleRepository.delete(role);
    }
}
