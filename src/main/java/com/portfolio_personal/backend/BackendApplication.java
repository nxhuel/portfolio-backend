package com.portfolio_personal.backend;

import com.portfolio_personal.backend.persistence.entity.*;
import com.portfolio_personal.backend.persistence.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

//    @Bean
//    CommandLineRunner init(UserRepository userRepository) {
//        return args -> {
//            // Permissions
//            PermissionEntity createPermission = PermissionEntity.builder()
//                    .name("CREATE")
//                    .build();
//
//            PermissionEntity readPermission = PermissionEntity.builder()
//                    .name("READ")
//                    .build();
//
//            // Roles
//            RoleEntity roleAdmin = RoleEntity.builder()
//                    .roleEnum(RoleEnum.ADMIN)
//                    .permissionList(Set.of(createPermission, readPermission))
//                    .build();
//
//            RoleEntity roleUser = RoleEntity.builder()
//                    .roleEnum(RoleEnum.USER)
//                    .permissionList(Set.of(readPermission))
//                    .build();
//            // Users
//            UserEntity userNahuel = UserEntity.builder()
//                    .username("nahuel")
//                    .password("$2a$10$qPrR5uHESStyupvTa2kA3uFOhKmRdZ4mtIWAKpnEEsFKe/yAikZSa")
//                    .description("java developer")
//                    .portfolioData(null)
//                    .projects(null)
//                    .isEnabled(true)
//                    .accountNoExpired(true)
//                    .accountNoLocked(true)
//                    .credentialNoExpired(true)
//                    .roleList(Set.of(roleAdmin))
//                    .build();
//
//            UserEntity userSantiago = UserEntity.builder()
//                    .username("santiago")
//                    .password("$2a$10$qPrR5uHESStyupvTa2kA3uFOhKmRdZ4mtIWAKpnEEsFKe/yAikZSa")
//                    .description("c# developer")
//                    .portfolioData(null)
//                    .projects(null)
//                    .isEnabled(true)
//                    .accountNoExpired(true)
//                    .accountNoLocked(true)
//                    .credentialNoExpired(true)
//                    .roleList(Set.of(roleUser))
//                    .build();
//
//            userRepository.saveAll(Set.of(userNahuel, userSantiago));
//        };
//    }
}
