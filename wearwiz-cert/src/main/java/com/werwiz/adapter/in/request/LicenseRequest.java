package com.werwiz.adapter.in.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LicenseRequest {

    @NotNull
    private Long licenseId;

    @NotNull
    private String name;
}
