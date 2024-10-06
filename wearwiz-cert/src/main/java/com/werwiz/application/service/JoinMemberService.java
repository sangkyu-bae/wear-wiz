package com.werwiz.application.service;

import com.wearwiz.common.UseCase;
import com.werwiz.adapter.in.request.JoinMemberRequest;
import com.werwiz.adapter.out.persistence.MemberMapper;
import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.application.port.in.JoinMemberUseCase;
import com.werwiz.application.port.out.JoinMemberPort;
import com.werwiz.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class JoinMemberService implements JoinMemberUseCase {

    private final JoinMemberPort joinMemberPort;

    private final MemberMapper memberMapper;
    @Override
    public Member joinMember(JoinMemberRequest request) {
        Member member = Member.createMember(
                null,
                request.getEmail(),
                null,
                null,
                null,
                request.getNickName(),
                request.getIntroduce(),
                null,
                null,
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                request.getName(),
                request.getPassword()
        );

        MemberEntity createMember = joinMemberPort.joinMember(member);

        return memberMapper.mapToDomain(createMember);
    }
}
