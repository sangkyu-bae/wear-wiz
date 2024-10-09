package com.werwiz.application.service;

import com.wearwiz.common.UseCase;
import com.werwiz.adapter.out.persistence.entity.LicenseEntity;
import com.werwiz.adapter.out.persistence.mapper.LicenseMapper;
import com.werwiz.application.port.in.FindLicenseUseCase;
import com.werwiz.application.port.out.FindLicensePort;
import com.werwiz.domain.Category;
import com.werwiz.domain.License;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class FindLicenseService implements FindLicenseUseCase {

    private final FindLicensePort findLicensePort;

    private final LicenseMapper licenseMapper;
    @Override
    @Transactional(readOnly = true)
    public List<License> findAll() {
        List<LicenseEntity> licenseEntityList = findLicensePort.findAll();
        return licenseEntityList.stream()
                .map(licenseEntity -> licenseMapper.mapToDomain(licenseEntity))
                .collect(Collectors.toList());
    }
}
