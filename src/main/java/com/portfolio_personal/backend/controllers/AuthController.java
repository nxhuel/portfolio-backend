package com.portfolio_personal.backend.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/get")
    public String getHello() {
        return "Hello World - GET";
    }

    @PostMapping("/post")
    public String postHello() {
        return "Hello World - POST";
    }

    @PutMapping("/put")
    public String putHello() {
        return "Hello World - PUT";
    }

    @DeleteMapping("/delete")
    public String deleteHello() {
        return "Hello World - DELETE";
    }
}
