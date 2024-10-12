package com.werwiz.application.port.in;

import com.werwiz.adapter.in.request.CategoryRequest;
import com.werwiz.adapter.in.request.LicenseRequest;
import com.werwiz.adapter.in.request.UpdateMemberRequest;
import com.werwiz.domain.Member;

public interface UpdateMemberUseCase {

    Member updateMember(long memberId, UpdateMemberRequest updateMemberRequest);

    Member addCategory(long memberId, CategoryRequest request);

    Member subtractCategory(long memberId, CategoryRequest request);

    Member addLicenseMember(long memberId, LicenseRequest request);
    Member subtractLicenseMember(long memberId, LicenseRequest request);
}
