package com.portfolio_personal.backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String description;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_portfolio")
    private PortfolioDataEntity portfolioData;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProjectEntity> projects = new ArrayList<>();
    // Atributos de spring security

    @Column(name = "is_enabled")
    private boolean isEnabled;
    @Column(name = "account_no_expired")
    private boolean accountNoExpired;
    @Column(name = "account_no_locked")
    private boolean accountNoLocked;
    @Column(name = "credential_no_expired")
    private boolean credentialNoExpired;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "id_user"), inverseJoinColumns = @JoinColumn(name = "id_role"))
    private Set<RoleEntity> roleList = new HashSet<>();

}
