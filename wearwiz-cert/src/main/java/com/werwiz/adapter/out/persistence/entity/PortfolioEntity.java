package com.werwiz.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="portfolioId")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_portfolio") @Builder
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long portfolioId;

    @OneToMany(mappedBy = "portfolio",fetch = FetchType.LAZY)
    private Set<ImageEntity> imageList;

    @OneToMany(mappedBy = "portfolio",fetch = FetchType.LAZY)
    private Set<PortfolioLicenseEntity> licenseList;

    public void addImage(ImageEntity imageEntity){
        imageEntity.setPortfolio(this);

        if(imageList == null){
            imageList = new HashSet<>();
        }

        imageList.add(imageEntity);
    }

    public void addImage(Set<ImageEntity> imageEntities){
        if(imageEntities == null){
            return;
        }
        for(ImageEntity imageEntity : imageEntities){
            this.addImage(imageEntity);
        }
    }

    public void addLicense(PortfolioLicenseEntity license){
        license.setPortfolio(this);

        if(licenseList == null){
            licenseList = new HashSet<>();
        } else{
           List<PortfolioLicenseEntity> licenseEntityList = licenseList.stream()
                    .filter(licenseEntity -> licenseEntity.getLicense().getLicenseId() == license.getLicense().getLicenseId())
                   .collect(Collectors.toList());

           if(licenseEntityList != null){
               return;
           }
        }

        licenseList.add(license);
    }
    public void addLicense(List<PortfolioLicenseEntity> licenseList){
        if(licenseList == null){
            return;
        }

        for(PortfolioLicenseEntity license : licenseList){
            this.addLicense(license);
        }
    }
}
