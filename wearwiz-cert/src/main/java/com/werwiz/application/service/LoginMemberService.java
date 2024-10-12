package com.werwiz.application.service;

import com.wearwiz.common.UseCase;
import com.wearwiz.common.error.ErrorException;
import com.werwiz.adapter.in.request.LoginMemberRequest;
import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.application.port.in.LoginMemberUseCase;
import com.werwiz.application.port.out.FindMemberPort;
import com.werwiz.domain.LoginMember;
import com.werwiz.infra.excption.MemberError;
import com.werwiz.infra.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class LoginMemberService implements LoginMemberUseCase {

    private final FindMemberPort findMemberPort;

    private final JwtTokenProvider jwtTokenProvider;

    private final RedisService redisService;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public LoginMember login(LoginMemberRequest loginRequest,HttpServletRequest request) {

        MemberEntity member = findMemberPort.findByEmail(loginRequest.getLoginEmail());

        if(member == null){
            throw new ErrorException(MemberError.MEMBER_NOT_FOUND,"LoginService - login");
        }

        if(!passwordEncoder.matches(loginRequest.getPassword(), member.getPassword())){
            throw new ErrorException(MemberError.MEMBER_NOT_FOUND,"LoginService - login");
        }

        List<String> roles = member.getRoleList().stream()
                .map(role->role.getRole().getRoleName())
                .collect(Collectors.toList());

        String userKey = String.valueOf(member.getMemberId());

        String accessToken = jwtTokenProvider.createJwtAccessToken(userKey,request.getRequestURI(),roles);
        String refreshToken = jwtTokenProvider.createJwtRefreshToken();
        Date expiredTime = jwtTokenProvider.getExpiredTime(accessToken);
        Date refreshExpiredTime = jwtTokenProvider.getExpiredTime(refreshToken);

        long currentTime = System.currentTimeMillis();
        long accessTokenExpirationDuration = expiredTime.getTime() - currentTime;
        long refreshTokenExpirationDuration = refreshExpiredTime.getTime() - currentTime;

        redisService.saveTokenToRedis(userKey,accessToken,accessTokenExpirationDuration);
        redisService.saveRefreshTokenToRedis(userKey,refreshToken,refreshTokenExpirationDuration);

        return LoginMember.createGenerateMember(accessToken,refreshToken,member.getNickName());
    }
}
