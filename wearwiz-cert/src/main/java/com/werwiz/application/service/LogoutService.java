package com.werwiz.application.service;

import com.wearwiz.common.UseCase;
import com.wearwiz.common.error.ErrorException;
import com.werwiz.application.port.in.LogoutUseCase;
import com.werwiz.infra.excption.MemberError;
import com.werwiz.infra.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class LogoutService implements LogoutUseCase {

    private final RedisService redisService;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${redis.refresh}")
    private String REFRESH;

    @Override
    public String logout(String token) {

        String userId = jwtTokenProvider.getUserId(token);

        String savedToken = redisService.getValues(userId);

        if(savedToken == null){
            throw new ErrorException(MemberError.NO_AUTHORIZATION_HEADER,"logoutservice- logout");
        }

        redisService.deleteValues(userId);
        redisService.deleteValues(REFRESH+userId);

        return "success";
    }
}
