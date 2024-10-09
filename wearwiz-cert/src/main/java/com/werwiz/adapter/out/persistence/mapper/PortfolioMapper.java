package com.werwiz.adapter.out.persistence.mapper;

import com.werwiz.adapter.out.persistence.entity.ImageEntity;
import com.werwiz.adapter.out.persistence.entity.LicenseEntity;
import com.werwiz.adapter.out.persistence.entity.PortfolioEntity;
import com.werwiz.adapter.out.persistence.entity.PortfolioLicenseEntity;
import com.werwiz.domain.Image;
import com.werwiz.domain.License;
import com.werwiz.domain.Portfolio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class PortfolioMapper {

    private final LicenseMapper licenseMapper;

    private final ImageMapper imageMapper;
    public Portfolio mapToDomain(PortfolioEntity portfolioEntity){


        List<License> licenses = new ArrayList<>();
        List<Image> images = new ArrayList<>();

        Set<PortfolioLicenseEntity> licenseEntityList =  portfolioEntity.getLicenseList();
        if(licenseEntityList != null){
                  licenses = licenseEntityList.stream()
                    .map(license -> licenseMapper.mapToDomain(license.getLicense()))
                    .collect(Collectors.toList());
        }

        Set<ImageEntity> imageEntities = portfolioEntity.getImageList();
        if(imageEntities != null){
            images = imageEntities.stream()
                    .map(image -> imageMapper.mapToDomain(image))
                    .collect(Collectors.toList());
        }

        return Portfolio.createPortfolio(images,licenses);
    }


}
