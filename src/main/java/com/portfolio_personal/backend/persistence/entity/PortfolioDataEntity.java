package com.portfolio_personal.backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "portfolio_data")
public class PortfolioDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String about_me;
    @OneToMany(mappedBy = "portfolio_data", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ExperienceEntity> experiences = new ArrayList<>();
    @OneToMany(mappedBy = "portfolio_data", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<SocialNetworksEntity> social_networks = new ArrayList<>();
}
