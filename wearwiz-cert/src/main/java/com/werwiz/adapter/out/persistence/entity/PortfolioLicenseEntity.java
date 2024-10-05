package com.werwiz.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_portfolio_license",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "MEMBER_CATEGORY_UNIQUE",
                        columnNames = {"portfolio_id", "category_id"}
                )
        })
@Builder
public class PortfolioLicenseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "portfolio_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PortfolioEntity portfolio;

    @JoinColumn(name = "license_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private LicenseEntity license;


}
