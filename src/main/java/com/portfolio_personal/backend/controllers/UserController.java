package com.portfolio_personal.backend.controllers;

import com.portfolio_personal.backend.persistence.entity.UserEntity;
import com.portfolio_personal.backend.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
//@PreAuthorize("denyAll()")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserEntity>> getUserList() {
        return new ResponseEntity<>(userService.getUserList(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/update-user/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UserEntity user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @DeleteMapping("/delete-user/{id}")
    public ResponseEntity<UserEntity> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
