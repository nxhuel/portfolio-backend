package com.portfolio_personal.backend.service.Impl;

import com.portfolio_personal.backend.persistence.entity.RoleEntity;
import com.portfolio_personal.backend.persistence.entity.UserEntity;
import com.portfolio_personal.backend.persistence.repository.RoleRepository;
import com.portfolio_personal.backend.persistence.repository.UserRepository;
import com.portfolio_personal.backend.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// Agregar repo de portfolioData y projects
// Arreglar el isEnabled xq siempre da false
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserEntity> getUserList() {
        List<UserEntity> userList = userRepository.findAll();
        if (userList.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No existen usuarios"
            );
        }
        return userList;
    }

    @Override
    public UserEntity getUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario con ID " + id + " no existe"
                ));
        return user;
    }

    @Override
    public UserEntity createUser(UserEntity user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        Set<RoleEntity> roles = user.getRoleList().stream()
                .map(role -> roleRepository.findById(role.getId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol con ID " + role.getId() + " no existe")))
                .collect(Collectors.toSet());
        user.setRoleList(roles);

        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUser(Long id, UserEntity updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(updatedUser.getUsername());
                    String hashedPassword = passwordEncoder.encode(updatedUser.getPassword());
                    user.setPassword(hashedPassword);
                    user.setDescription(updatedUser.getDescription());
                    // Actualizar atributos de seguridad
                    user.setEnabled(updatedUser.isEnabled());
                    user.setAccountNoExpired(updatedUser.isAccountNoExpired());
                    user.setAccountNoLocked(updatedUser.isAccountNoLocked());
                    user.setCredentialNoExpired(updatedUser.isCredentialNoExpired());
                    // Listas de usuario
                    Set<RoleEntity> roles = updatedUser.getRoleList().stream()
                            .map(role -> roleRepository.findById(role.getId())
                                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rol con ID " + role.getId() + " no existe")))
                            .collect(Collectors.toSet());
                    user.setRoleList(roles);

                    return userRepository.save(user);
                })
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario con ID " + id + " no existe"
                ));
    }

    @Override
    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuario con ID " + id + " no existe"
                ));
        userRepository.delete(user);
    }
}
