package com.werwiz.application.service;

import com.wearwiz.common.UseCase;
import com.werwiz.adapter.in.request.JoinMemberRequest;
import com.werwiz.adapter.out.persistence.mapper.CategoryMapper;
import com.werwiz.adapter.out.persistence.mapper.LicenseMapper;
import com.werwiz.adapter.out.persistence.mapper.MemberMapper;
import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.application.port.in.JoinMemberUseCase;
import com.werwiz.application.port.out.JoinMemberPort;
import com.werwiz.domain.Category;
import com.werwiz.domain.License;
import com.werwiz.domain.Member;
import com.werwiz.domain.Portfolio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Slf4j
@RequiredArgsConstructor
public class JoinMemberService implements JoinMemberUseCase {

    private final JoinMemberPort joinMemberPort;

    private final MemberMapper memberMapper;

    private final CategoryMapper categoryMapper;

    private final LicenseMapper licenseMapper;
    @Override
    public Member joinMember(JoinMemberRequest request) {
        List<Category> categories = null;
        List<License> licenses = null;
        if(request.getCategories() != null){
            categories = request.getCategories().stream()
                    .map(category -> categoryMapper.mapToDomain(category))
                    .collect(Collectors.toList());

        }

        if(request.getLicenses() != null){
            licenses = request.getLicenses().stream()
                    .map(license -> licenseMapper.mapToDomain(license))
                    .collect(Collectors.toList());
        }

        Portfolio portfolio = Portfolio.createPortfolio(null,licenses);

        Member member = Member.createMember(
                null,
                request.getEmail(),
                request.getArea(),
                null,
                Double.valueOf(0),
                request.getNickName(),
                request.getIntroduce(),
                categories,
                null,
                portfolio,
                LocalDateTime.now(),
                LocalDateTime.now(),
                request.getName(),
                request.getPassword()
        );


        MemberEntity createMember = joinMemberPort.joinMember(member);

        return memberMapper.mapToDomain(createMember);
    }

}
