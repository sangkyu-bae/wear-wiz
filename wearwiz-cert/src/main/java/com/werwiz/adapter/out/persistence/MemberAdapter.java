package com.werwiz.adapter.out.persistence;

import com.wearwiz.common.PersistenceAdapter;
import com.wearwiz.common.error.ErrorException;
import com.werwiz.adapter.out.persistence.entity.*;
import com.werwiz.adapter.out.persistence.mapper.LicenseMapper;
import com.werwiz.adapter.out.persistence.repository.*;
import com.werwiz.application.port.out.FindMemberPort;
import com.werwiz.application.port.out.JoinMemberPort;
import com.werwiz.domain.*;
import com.werwiz.infra.excption.MemberError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class MemberAdapter implements JoinMemberPort, FindMemberPort {

    private final MemberEntityRepository memberEntityRepository;
    private final MemberCategoryEntityRepository memberCategoryEntityRepository;
    private final PortfolioLicenseEntityRepository portfolioLicenseEntityRepository;
    private final PortfolioEntityRepository portfolioEntityRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public MemberEntity joinMember(Member member) {
        Set<MemberCategoryEntity> memberCategoryList = null;
        PortfolioEntity portfolio = null;
        if(member.getCategorys() != null){
            memberCategoryList = new HashSet<>();
            for(Category category : member.getCategorys()){
                CategoryEntity categoryEntity = modelMapper.map(category,CategoryEntity.class);
                memberCategoryList.add(
                        MemberCategoryEntity.builder()
                                .category(categoryEntity)
                                .build()
                );

            }
            List<MemberCategoryEntity> saveCategory = memberCategoryEntityRepository.saveAll(memberCategoryList);
            memberCategoryList = new HashSet<>(saveCategory);
        }

        if(member.getPortfolio() != null){
            portfolio = setPortfolio(member.getPortfolio());
            Set<PortfolioLicenseEntity> licenseEntities = new HashSet<>();


            if(portfolio.getLicenseList() != null){
                List<PortfolioLicenseEntity> savedLicenses = portfolioLicenseEntityRepository.saveAll(portfolio.getLicenseList());
                licenseEntities = new HashSet<>(savedLicenses);
            }

            portfolio = portfolioEntityRepository.save(portfolio);
        }

        MemberEntity memberEntity = MemberEntity.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .area(member.getArea())
                .counsel(member.getCounsel())
                .rate(member.getRate())
                .name(member.getNickName())
                .nickName(member.getNickName())
                .introduce(member.getIntroduce())
                .portfolio(portfolio)
                .password(passwordEncoder.encode(member.getPassword()))
                .createAt(member.getCreateAt())
                .updateAt(member.getUpdateAt())
                .build();
        memberEntity =  memberEntityRepository.save(memberEntity);

        if(memberCategoryList != null) {
            memberEntity.addCategory(memberCategoryList);
        }

        return memberEntity;
    }

    private PortfolioEntity setPortfolio(Portfolio portfolio){

        Set<PortfolioLicenseEntity> portfolioLicense = null;

        if(!portfolio.getLicenses().isEmpty()){
            portfolioLicense = new HashSet<>();

            for(License license : portfolio.getLicenses()){
                LicenseEntity licenseEntity = modelMapper.map(license,LicenseEntity.class);
                licenseEntity.setLicenseId(license.getId());

                PortfolioLicenseEntity portfolioLicenseEntity = PortfolioLicenseEntity.builder()
                        .license(licenseEntity)
                        .build();

                portfolioLicense.add(portfolioLicenseEntity);
            }

        }

        PortfolioEntity portfolioEntity = PortfolioEntity.builder()
                .licenseList(portfolioLicense)
                .build();

        return portfolioEntity;
    }

    @Override
    public MemberEntity findById(long id) {
        return memberEntityRepository.findById(id)
                .orElseThrow(()->new ErrorException(MemberError.MEMBER_NOT_FOUND,"findById"));
    }

    @Override
    public MemberEntity findByEmailAndPassword(String email, String password) {
        password = passwordEncoder.encode(password);
        return memberEntityRepository.findByEmailAndPassword(email,password);
    }
}
