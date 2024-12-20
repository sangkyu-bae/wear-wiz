package com.werwiz.adapter.in.web;

import com.wearwiz.common.WebAdapter;
import com.wearwiz.common.error.ErrorException;
import com.werwiz.adapter.in.request.*;
import com.werwiz.application.port.in.*;
import com.werwiz.domain.Image;
import com.werwiz.domain.LoginMember;
import com.werwiz.domain.Member;
import com.werwiz.domain.vailadtor.JoinMemberValidator;
import com.werwiz.infra.excption.MemberError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final UpdateMemberUseCase updateMemberUseCase;
    @InitBinder("joinMemberRequest")
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(joinMemberValidator);
    }

    @PostMapping("/member/v1/join")
    @Operation(summary = "회원 가입", description = "회원 가입 하기 not null은 필수 값 입니다.")
    @Parameters({
            @Parameter(name = "email", description = "이메일(not null)", example = "chrome123@naver.com"),
            @Parameter(name = "password", description = "4자~15자 이내 패스워드(not null)", example = "abcd1234"),
            @Parameter(name = "confirmPassword", description = "패스워드 확인(not null)", example = "abcd1234"),
            @Parameter(name = "nickName", description = "2~10자 이내 닉네임 (not null)", example = "닉네임"),
            @Parameter(name = "introduce", description = "자기 소개", example = "안녕하세요 자기 소개 입니다"),
            @Parameter(name = "area", description = "활동 지역", example = "대전시(추후 area API 드릴게요)"),
            @Parameter(name = "licenses", description = "자격증 /member/v1/license 여기 API에서 호출해서 나온값들 로 세팅해주세요", example = ""),
            @Parameter(name = "categories", description = "카테고리 /member/v1/category 여기 API에서 호출해서 나온값들 로 세팅해주세요", example = "")
    })
    public ResponseEntity<Member> joinMember(@RequestBody @Valid JoinMemberRequest joinMemberRequest, Errors errors){

        if(errors.hasErrors()){
            throw new ErrorException(MemberError.MEMBER_FORM_NO_VALIDATE,"joinMember");
        }

        Member createMember = joinMemberUseCase.joinMember(joinMemberRequest);

        return ResponseEntity.ok().body(createMember);
    }

    @Operation(summary = "회원 포토폴리오 추가", description =
            "  accessToken을 헤더로 던져야합니다")
    @PostMapping(path = "/member/v2/portfolio-img", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Image>> registerImgByMember(@RequestParam("files") List<MultipartFile> files,
                                                           @RequestHeader("userId") Long userId){
        List<Image> uploadImage = registerImgUseCase.registerImgByMember(files,userId);

        return ResponseEntity.ok().body(uploadImage);
    }

    @PostMapping("/member/v1/login")
    @Operation(summary = "로그인", description =
            " JWT 토큰 을 리턴하며, 나온 accessToken, refrshToken을 저장하고 있어야 해요.")
    public ResponseEntity<LoginMember> login(@RequestBody @Valid LoginMemberRequest loginRequest,
                                             Errors errors,
                                             HttpServletRequest request){

        if(errors.hasErrors()){
            throw new ErrorException(MemberError.MEMBER_LOGIN_VALIDATE,"login");
        }
        LoginMember member = loginMemberUseCase.login(loginRequest, request);

        return ResponseEntity.ok().body(member);
    }

    @DeleteMapping("/member/v2/logout")
    @Operation(summary = "로그아웃", description =
            "로그아웃 입니다, 로그인한뒤 나온 accessToken 값 입력 실제 API 보낼때는 Header에 실어 보내야해요." ,
            security = {@SecurityRequirement(name = "accessToken")}
    )
    public ResponseEntity<String> logout(@RequestHeader("userId") Long userId,
                                         @RequestHeader("jwt") String jwt){
        String status = logoutUseCase.logout(jwt);

        return ResponseEntity.ok().body(status);
    }

    @PatchMapping("/member/v2")
    @Operation(summary = "회원수정", description =
            "기본 회원수정 입니다, 로그인한뒤 나온 accessToken 값 입력 실제 API 보낼때는 Header에 실어 보내야해요."
    )
    public ResponseEntity<Member> updateMember(@RequestBody UpdateMemberRequest updateMemberRequest,
                                               @RequestHeader("userId") Long userId){
        Member updateMember = updateMemberUseCase.updateMember(userId,updateMemberRequest);

        return ResponseEntity.ok().body(updateMember);
    }

    @PutMapping("/member/v2/category/add")
    @Operation(summary = "회원수정 (카테고리 추가)",description =
            "회원의 카테고리를 추가 합니다")
    public ResponseEntity<Member> addCategoryToMember(@RequestBody CategoryRequest request,
                                                      @RequestHeader("userId") Long userId){
        Member addCategoryMember = updateMemberUseCase.addCategory(userId,request);

        return ResponseEntity.ok().body(addCategoryMember);
    }

    @PutMapping("/member/v2/category/subtract")
    @Operation(summary = "회원수정 (카테고리 삭제)",description =
            "회원의 카테고리를 삭제 합니다")
    public ResponseEntity<Member> subtractCategoryToMember(@RequestBody CategoryRequest request,
                                                           @RequestHeader("userId") Long userId){
        Member subtractCategoryMember = updateMemberUseCase.subtractCategory(userId,request);

        return ResponseEntity.ok().body(subtractCategoryMember);
    }

    @PutMapping("/member/v2/license/add")
    @Operation(summary = "회원수정 (자격증 추가)",description =
            "회원의 자격증을 추가 합니다")
    public ResponseEntity<Member> addLicenseToMember(@RequestBody LicenseRequest request,
                                                     @RequestHeader("userId") Long userId){

        Member addLicenseMember = updateMemberUseCase.addLicenseMember(userId,request);

        return ResponseEntity.ok().body(addLicenseMember);

    }

    @PutMapping("/member/v2/license/remove")
    @Operation(summary = "회원수정 (자격증 삭제)",description =
            "회원의 자격증을 삭제 합니다")
    public ResponseEntity<Member> removeLicenseToMember(@RequestBody LicenseRequest request,
                                                     @RequestHeader("userId") Long userId){

        Member addLicenseMember = updateMemberUseCase.subtractLicenseMember(userId,request);

        return ResponseEntity.ok().body(addLicenseMember);

    }

}
