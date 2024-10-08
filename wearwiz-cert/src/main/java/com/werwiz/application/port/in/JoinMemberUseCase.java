package com.werwiz.application.port.in;

import com.werwiz.adapter.in.request.JoinMemberRequest;
import com.werwiz.domain.Member;

public interface JoinMemberUseCase {

    Member joinMember(JoinMemberRequest request);

}
