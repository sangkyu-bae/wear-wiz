package com.werwiz.application.port.out;

import com.werwiz.adapter.out.persistence.entity.LicenseEntity;

import java.util.List;

public interface FindLicensePort {

    List<LicenseEntity> findAll();
}
