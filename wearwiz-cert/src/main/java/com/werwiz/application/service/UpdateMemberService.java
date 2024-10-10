package com.werwiz.application.service;

import com.wearwiz.common.UseCase;
import com.werwiz.adapter.in.request.UpdateMemberRequest;
import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.adapter.out.persistence.mapper.MemberMapper;
import com.werwiz.application.port.in.UpdateMemberUseCase;
import com.werwiz.application.port.out.UpdateMemberPort;
import com.werwiz.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class UpdateMemberService implements UpdateMemberUseCase {

    private final UpdateMemberPort updateMemberPort;
    private final MemberMapper memberMapper;
    @Override
    public Member updateMember(long memberId, UpdateMemberRequest updateMemberRequest) {
        Member member = Member.createMember(
                memberId,
                null,
                updateMemberRequest.getArea(),
                updateMemberRequest.getConsul(),
                null,
                null,
                updateMemberRequest.getIntroduce(),
                null,
                null,
                null
                ,null
                , LocalDateTime.now(),
                null,
                null
        );

        MemberEntity memberEntity = updateMemberPort.updateMember(member);

        return memberMapper.mapToDomain(memberEntity);

    }
}
