package com.werwiz.adapter.in.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UpdateMemberRequest {

    private String introduce;

    private String area;

    private int consul;

    private List<LicenseRequest> licenses;

    private List<CategoryRequest> categoryRequests;
}
