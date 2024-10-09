package com.werwiz.adapter.out.persistence;

import com.wearwiz.common.PersistenceAdapter;
import com.werwiz.adapter.out.persistence.entity.LicenseEntity;
import com.werwiz.adapter.out.persistence.repository.LicenseEntityRepository;
import com.werwiz.application.port.out.FindLicensePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class LicenseAdapter implements FindLicensePort {

    private final LicenseEntityRepository licenseEntityRepository;

    @Override
    public List<LicenseEntity> findAll() {
        return licenseEntityRepository.findAll();
    }
}
