package com.portfolio_personal.backend.service;

import com.portfolio_personal.backend.persistence.entity.UserEntity;

import java.util.List;

public interface IUserService {
    List<UserEntity> getUserList();
    UserEntity getUser(Long id);
    UserEntity createUser(UserEntity user);
    UserEntity updateUser(Long id, UserEntity updatedUser);
    void deleteUser(Long id);
}
