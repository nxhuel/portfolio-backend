package com.portfolio_personal.backend.controllers;

import com.portfolio_personal.backend.persistence.entity.RoleEntity;
import com.portfolio_personal.backend.service.Impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@PreAuthorize("denyAll()")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("/roles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoleEntity>> getRoleList() {
        return ResponseEntity.ok(roleService.getRoleList());
    }

    @GetMapping("/role/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleEntity> getRole(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getRole(id));
    }

    @PostMapping("/create-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleEntity> createRole(@RequestBody RoleEntity role) {
        return ResponseEntity.ok(roleService.createRole(role));
    }

    @PutMapping("/update-role/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleEntity> updateRole(@PathVariable Long id, @RequestBody RoleEntity role) {
        RoleEntity updatedRole = roleService.updateRole(id, role);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/detele-role/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoleEntity> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
