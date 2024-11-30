package com.portfolio_personal.backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "publication")
public class PublicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    private String description;
    @ManyToOne
    @JoinColumn(name = "id_portfolio_data", nullable = false)
    private PortfolioDataEntity portfolio_data;
}
