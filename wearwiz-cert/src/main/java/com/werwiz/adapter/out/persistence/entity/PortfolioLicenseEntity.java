package com.werwiz.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="id",callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_portfolio_license",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "PORTFOLIO_LICENSE_UNIQUE",
                        columnNames = {"portfolio_id", "license_id"}
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

    private boolean isUse;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PortfolioLicenseEntity that = (PortfolioLicenseEntity) o;

        if (id == null || that.id == null) {
            return portfolio.equals(that.portfolio) && license.equals(that.license);
        }

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            int portfolioHash = (portfolio != null) ? portfolio.hashCode() : 0;
            int licenseHash = (license != null) ? license.hashCode() : 0;
            return portfolioHash + licenseHash;
        }
        return id.hashCode();
    }

    public boolean isContain(long licenseId){
        if(this.license.getLicenseId() == licenseId){
            return true;
        }

        return false;
    }
}
