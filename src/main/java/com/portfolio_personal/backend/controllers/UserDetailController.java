package com.portfolio_personal.backend.controllers;

import com.portfolio_personal.backend.persistence.entity.UserEntity;
import com.portfolio_personal.backend.persistence.repository.UserRepository;
import com.portfolio_personal.backend.service.Impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserDetailController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @GetMapping("/user/{username}")
    public ResponseEntity<String> getUserDetails(@PathVariable String username) {
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        StringBuilder response = new StringBuilder();

        // Información del usuario
        response.append("Usuario encontrado: ").append(userDetails.getUsername()).append("\n");
        response.append("Contraseña: ").append(userDetails.getPassword()).append("\n");
        response.append("Habilitado: ").append(userDetails.isEnabled()).append("\n");
        response.append("Cuenta Expirada: ").append(!userDetails.isAccountNonExpired()).append("\n");
        response.append("Cuenta Bloqueada: ").append(!userDetails.isAccountNonLocked()).append("\n");
        response.append("Credenciales Expiradas: ").append(!userDetails.isCredentialsNonExpired()).append("\n");

        // Roles del usuario
        response.append("Roles: ");
        userDetails.getAuthorities().forEach(authority -> response.append(authority.getAuthority()).append(", "));

        return ResponseEntity.ok(response.toString());
    }

}
