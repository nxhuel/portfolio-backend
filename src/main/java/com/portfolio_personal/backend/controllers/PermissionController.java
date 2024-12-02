package com.portfolio_personal.backend.controllers;

import com.portfolio_personal.backend.persistence.entity.PermissionEntity;
import com.portfolio_personal.backend.service.Impl.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
//@PreAuthorize("denyAll()")
public class PermissionController {
    @Autowired
    private PermissionServiceImpl permissionService;

    @GetMapping("/permissions")
    public ResponseEntity<List<PermissionEntity>> getPermissionList() {
        return ResponseEntity.ok(permissionService.getPermissionList());
    }

    @GetMapping("/permission/{id}")
    public ResponseEntity<PermissionEntity> getPermission(@PathVariable Long id) {
        return permissionService.getPermission(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create-permission")
    public ResponseEntity<PermissionEntity> createPermission(@RequestBody PermissionEntity permission) {
        PermissionEntity createdPermission = permissionService.createPermission(permission);
        return ResponseEntity.ok(createdPermission);
    }

    @PutMapping("/update-permission/{id}")
    public ResponseEntity<PermissionEntity> updatePermission(@PathVariable Long id, @RequestBody PermissionEntity permission) {
        PermissionEntity updatedPermission = permissionService.updatePermission(id, permission);
        return ResponseEntity.ok(updatedPermission);
    }

    @DeleteMapping("/delete-permission/{id}")
    public ResponseEntity<PermissionEntity> deletePermission(@PathVariable Long id) {
        permissionService.deletePermission(id);
        return ResponseEntity.noContent().build();
    }
}
