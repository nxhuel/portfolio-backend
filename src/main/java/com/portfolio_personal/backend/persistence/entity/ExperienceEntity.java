package com.portfolio_personal.backend.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "experience")
public class ExperienceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String company;
    private String description;
    private String date;
    @ManyToOne
    @JoinColumn(name = "id_portfolio_data", nullable = false)
    private PortfolioDataEntity portfolio_data;
}
