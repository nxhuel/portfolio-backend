package com.portfolio_personal.backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "social_networks")
public class SocialNetworksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String socialNetwork;
    private String url;
    @ManyToOne
    @JoinColumn(name = "id_portfolio_data", nullable = false)
    private PortfolioDataEntity portfolio_data;
}
