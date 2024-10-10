package com.werwiz.adapter.in.web;

import com.wearwiz.common.WebAdapter;
import com.wearwiz.common.error.ErrorException;
import com.werwiz.adapter.in.request.JoinMemberRequest;
import com.werwiz.adapter.in.request.LoginMemberRequest;
import com.werwiz.adapter.in.request.UpdateMemberRequest;
import com.werwiz.application.port.in.JoinMemberUseCase;
import com.werwiz.application.port.in.LoginMemberUseCase;
import com.werwiz.application.port.in.LogoutUseCase;
import com.werwiz.application.port.in.RegisterImgUseCase;
import com.werwiz.domain.Image;
import com.werwiz.domain.LoginMember;
import com.werwiz.domain.Member;
import com.werwiz.domain.vailadtor.JoinMemberValidator;
import com.werwiz.infra.excption.MemberError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@WebAdapter
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final JoinMemberValidator joinMemberValidator;

    private final JoinMemberUseCase joinMemberUseCase;

    private final RegisterImgUseCase registerImgUseCase;

    private final LoginMemberUseCase loginMemberUseCase;

    private final LogoutUseCase logoutUseCase;
    @InitBinder("joinMemberRequest")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(joinMemberValidator);
    }

    @PostMapping("/member/join")
    public ResponseEntity<Member> joinMember(@RequestBody @Valid JoinMemberRequest joinMemberRequest, Errors errors){

        if(errors.hasErrors()){
            throw new ErrorException(MemberError.MEMBER_FORM_NO_VALIDATE,"joinMember");
        }

        Member createMember = joinMemberUseCase.joinMember(joinMemberRequest);

        return ResponseEntity.ok().body(createMember);
    }

    @PostMapping("/member/portfolio-img/{memberId}")
    public ResponseEntity<List<Image>> registerImgByMember(@RequestParam("files") List<MultipartFile> files,
                                                     @PathVariable("memberId") Long memberId){
        List<Image> uploadImage = registerImgUseCase.registerImgByMember(files,memberId);

        return ResponseEntity.ok().body(uploadImage);
    }

    @PostMapping("/member/login")
    public ResponseEntity<LoginMember> login(@RequestBody @Valid LoginMemberRequest loginRequest,
                                             Errors errors,
                                             HttpServletRequest request){

        if(errors.hasErrors()){
            throw new ErrorException(MemberError.MEMBER_LOGIN_VALIDATE,"login");
        }
        LoginMember member = loginMemberUseCase.login(loginRequest, request);

        return ResponseEntity.ok().body(member);
    }

    /**
     * TODO: 추후 sprig gateway에서 jwt 검증예정
     * */
    @DeleteMapping("/member/logout")
    public ResponseEntity<String> logout(ServerHttpRequest request){
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new ErrorException(MemberError.NO_AUTHORIZATION_HEADER,"logout");
        }

        String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        String jwt = authorizationHeader.replace("Bearer", "");
        String status = logoutUseCase.logout(jwt);

        return ResponseEntity.ok().body(status);
    }


    /**
     * TODO: 추후 spring gateway에서 jwt 검증예정
     * */
    @PatchMapping("/member")
    public ResponseEntity<Member> updateMember(@RequestBody UpdateMemberRequest updateMemberRequest,
                                               ServerHttpRequest request){
        if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            throw new ErrorException(MemberError.NO_AUTHORIZATION_HEADER,"logout");
        }

        String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
        String jwt = authorizationHeader.replace("Bearer", "");


        return ResponseEntity.ok().body(null);
    }
}
