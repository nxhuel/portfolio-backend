package com.portfolio_personal.backend.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@PreAuthorize("denyAll()")
public class AuthController {

    @GetMapping("/get")
    @PreAuthorize("permitAll()")
    public String getHello() {
        return "Hello World - GET";
    }

    @PostMapping("/post")
    @PreAuthorize("hasRole('ADMIN')")
    public String postHello() {
        return "Hello World - POST";
    }

    @PutMapping("/put")
    public String putHello() {
        return "Hello World - PUT";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public String deleteHello() {
        return "Hello World - DELETE";
    }
}
