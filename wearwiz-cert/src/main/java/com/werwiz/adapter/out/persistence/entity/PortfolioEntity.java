package com.werwiz.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_portfolio") @Builder
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long portfolioId;

    @OneToMany(mappedBy = "portfolio",fetch = FetchType.LAZY)
    private Set<ImageEntity> imageEntities;

    @OneToMany(mappedBy = "portfolio",fetch = FetchType.LAZY)
    private Set<PortfolioLicenseEntity> licenseList;
}
