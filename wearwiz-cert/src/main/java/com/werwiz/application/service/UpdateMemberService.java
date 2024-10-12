package com.werwiz.application.service;

import com.wearwiz.common.UseCase;
import com.werwiz.adapter.in.request.CategoryRequest;
import com.werwiz.adapter.in.request.LicenseRequest;
import com.werwiz.adapter.in.request.UpdateMemberRequest;
import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.adapter.out.persistence.mapper.*;
import com.werwiz.application.port.in.UpdateMemberUseCase;
import com.werwiz.application.port.out.UpdateMemberPort;
import com.werwiz.domain.Category;
import com.werwiz.domain.License;
import com.werwiz.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class UpdateMemberService implements UpdateMemberUseCase {

    private final UpdateMemberPort updateMemberPort;
    private final MemberMapper memberMapper;
    private final LicenseMapper licenseMapper;
    private final CategoryMapper categoryMapper;
    @Override
    @Transactional
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

    @Override
    @Transactional
    public Member addCategory(long memberId, CategoryRequest request) {
        Category category = categoryMapper.mapToDomain(request);
        MemberEntity updateEntity = updateMemberPort.addCategoryToMember(memberId,category);

        return memberMapper.mapToDomain(updateEntity);
    }

    @Override
    @Transactional
    public Member subtractCategory(long memberId, CategoryRequest request) {

        Category category = categoryMapper.mapToDomain(request);
        MemberEntity updateMember = updateMemberPort.subtractCategoryToMember(memberId,category);
        return memberMapper.mapToDomain(updateMember);
    }

    @Override
    @Transactional
    public Member addLicenseMember(long memberId, LicenseRequest request) {
        License license = licenseMapper.mapToDomain(request);
        MemberEntity updateMember = updateMemberPort.addLicenseToMember(memberId,license);
        return memberMapper.mapToDomain(updateMember);
    }

    @Override
    @Transactional
    public Member subtractLicenseMember(long memberId, LicenseRequest request) {
        License license = licenseMapper.mapToDomain(request);
        MemberEntity updateMember = updateMemberPort.subtractLicenseToMember(memberId,license);
        return memberMapper.mapToDomain(updateMember);
    }
}
