package com.portfolio_personal.backend.service.Impl;

import com.portfolio_personal.backend.persistence.entity.PermissionEntity;
import com.portfolio_personal.backend.persistence.repository.PermissionRepository;
import com.portfolio_personal.backend.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<PermissionEntity> getPermissionList() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<PermissionEntity> getPermission(Long id) {
        return permissionRepository.findById(id);
    }

    public PermissionEntity createPermission(PermissionEntity permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public PermissionEntity updatePermission(Long id, PermissionEntity updatedPermission) {
        return permissionRepository.findById(id)
                .map(permission -> {
                    permission.setName(updatedPermission.getName());
                    return permissionRepository.save(permission);
                })
                .orElseThrow(() -> new IllegalArgumentException("Permiso con ID " + id + " no encontrado"));
    }

    @Override
    public void deletePermission(Long id) {
        PermissionEntity permission = permissionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Permiso no encontrado con ID: " + id
                ));
        permissionRepository.delete(permission);
    }
}
