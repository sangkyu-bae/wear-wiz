package com.werwiz.application.port.in;

import com.werwiz.domain.License;

import java.util.List;

public interface FindLicenseUseCase {
    List<License> findAll();
}
