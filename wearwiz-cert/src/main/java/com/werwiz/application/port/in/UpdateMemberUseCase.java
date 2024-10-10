package com.werwiz.application.port.in;

import com.werwiz.adapter.in.request.UpdateMemberRequest;
import com.werwiz.domain.Member;

public interface UpdateMemberUseCase {

    Member updateMember(long memberId, UpdateMemberRequest updateMemberRequest);
}
