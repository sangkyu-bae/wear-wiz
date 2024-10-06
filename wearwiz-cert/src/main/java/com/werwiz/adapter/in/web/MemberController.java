package com.werwiz.adapter.in.web;

import com.wearwiz.common.WebAdapter;
import com.wearwiz.common.error.ErrorException;
import com.werwiz.adapter.in.request.JoinMemberRequest;
import com.werwiz.application.port.in.JoinMemberUseCase;
import com.werwiz.domain.Member;
import com.werwiz.domain.vailadtor.JoinMemberValidator;
import com.werwiz.infra.excption.MemberError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final JoinMemberValidator joinMemberValidator;

    private final JoinMemberUseCase joinMemberUseCase;
    @InitBinder("joinMemberRequest")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(joinMemberValidator);
    }

    @PostMapping("/member/join")
    public ResponseEntity<Member> joinMember(@RequestBody JoinMemberRequest joinMemberRequest, Errors errors){

        if(errors.hasErrors()){
            throw new ErrorException(MemberError.MEMBER_FORM_NO_VALIDATE,"joinMember");
        }

        Member createMember = joinMemberUseCase.joinMember(joinMemberRequest);

        return ResponseEntity.ok().body(createMember);
    }
}
