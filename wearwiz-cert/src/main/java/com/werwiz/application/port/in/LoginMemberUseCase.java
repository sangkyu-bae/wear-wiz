package com.werwiz.application.port.in;

import com.werwiz.adapter.in.request.LoginMemberRequest;
import com.werwiz.domain.LoginMember;
import jakarta.servlet.http.HttpServletRequest;

public interface LoginMemberUseCase {

    LoginMember login(LoginMemberRequest loginRequest, HttpServletRequest request);
}
