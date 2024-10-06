package com.werwiz.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class License {

    private final Long licenseId;

    private final String name;

    public static License createLicense(Long id, String name){
        return new License(id,name);
    }
}
