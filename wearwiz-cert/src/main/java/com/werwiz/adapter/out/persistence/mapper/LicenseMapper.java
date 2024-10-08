package com.werwiz.adapter.out.persistence.mapper;

import com.werwiz.adapter.in.request.JoinMemberRequest;
import com.werwiz.domain.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LicenseMapper {

    public License mapToDomain(JoinMemberRequest.License license){
        return License.createLicense(license.getLicenseId(), license.getName());
    }
}
