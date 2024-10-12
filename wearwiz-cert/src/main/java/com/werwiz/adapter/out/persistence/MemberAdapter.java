package com.werwiz.adapter.out.persistence;

import com.wearwiz.common.PersistenceAdapter;
import com.wearwiz.common.error.ErrorException;
import com.werwiz.adapter.out.persistence.entity.*;
import com.werwiz.adapter.out.persistence.mapper.LicenseMapper;
import com.werwiz.adapter.out.persistence.repository.*;
import com.werwiz.application.port.out.FindMemberPort;
import com.werwiz.application.port.out.JoinMemberPort;
import com.werwiz.application.port.out.UpdateMemberPort;
import com.werwiz.domain.*;
import com.werwiz.infra.excption.MemberError;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
@Slf4j
public class MemberAdapter implements JoinMemberPort, FindMemberPort, UpdateMemberPort {

    private final MemberEntityRepository memberEntityRepository;
    private final MemberCategoryEntityRepository memberCategoryEntityRepository;
    private final PortfolioLicenseEntityRepository portfolioLicenseEntityRepository;
    private final PortfolioEntityRepository portfolioEntityRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final LicenseEntityRepository licenseEntityRepository;


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
    public MemberEntity findByEmail(String email) {
        return memberEntityRepository.findByEmail(email);
    }

    @Override
    public MemberEntity updateMember(Member updateMember) {
        MemberEntity memberEntity = this.findById(updateMember.getMemberId());

        if(updateMember.getArea() != null){
            memberEntity.setArea(updateMember.getArea());
        }

        if(updateMember.getIntroduce() != null){
            memberEntity.setIntroduce(updateMember.getIntroduce());
        }

        memberEntity.setUpdateAt(LocalDateTime.now());

        return memberEntityRepository.save(memberEntity);
    }

    @Override
    public MemberEntity addCategoryToMember(long memberId, Category category) {
        MemberEntity memberEntity = this.findById(memberId);
        Set<MemberCategoryEntity> memberCategorys = memberEntity.getCategory();

        if(memberCategorys != null){
            for(MemberCategoryEntity categoryEntity : memberCategorys){
                boolean isContain = categoryEntity.isContain(category.getId());

                if(isContain){
                    return memberEntity;
                }
            }
        }

        CategoryEntity categoryEntity = CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        MemberCategoryEntity memberCategoryEntity = MemberCategoryEntity.builder()
                        .category(categoryEntity)
                        .build();

        memberCategoryEntity = memberCategoryEntityRepository.save(memberCategoryEntity);

        memberEntity.addCategory(memberCategoryEntity);

        memberEntity.setUpdateAt(LocalDateTime.now());
        return memberEntityRepository.save(memberEntity);
    }

    @Override
    public MemberEntity subtractCategoryToMember(long memberId, Category category) {
        MemberEntity memberEntity = this.findById(memberId);
        Set<MemberCategoryEntity> memberCategorys = memberEntity.getCategory();

        if(memberCategorys == null){
            return memberEntity;
        }

        boolean isContain = false;
        for(MemberCategoryEntity categoryEntity : memberCategorys){
            isContain = categoryEntity.isContain(category.getId());

            if(isContain){
                memberEntity.subtractCategory(categoryEntity);
                memberCategoryEntityRepository.delete(categoryEntity);
                break;
            }
        }

        if(isContain){
            memberEntity.setUpdateAt(LocalDateTime.now());
            memberEntity = memberEntityRepository.save(memberEntity);
        }
        return memberEntity;
    }

    @Override
    public MemberEntity addLicenseToMember(long memberId, License license) {
        MemberEntity memberEntity = this.findById(memberId);
        PortfolioEntity portfolioEntity = memberEntity.getPortfolio();

        if(portfolioEntity != null){
            Set<PortfolioLicenseEntity> licenseEntities=   portfolioEntity.getLicenseList();

            if(licenseEntities != null){
                for(PortfolioLicenseEntity portfolioLicenseEntity : licenseEntities){
                    boolean isContain = portfolioLicenseEntity.isContain(license.getId());

                    if(isContain){
                        return memberEntity;
                    }
                }
            }
        }else {
            portfolioEntity = PortfolioEntity.builder()
                  .build();
        }



        LicenseEntity licenseEntity =  LicenseEntity.builder()
                .licenseId(license.getId())
                .name(license.getName())
                .build();

        PortfolioLicenseEntity portfolioLicenseEntity = PortfolioLicenseEntity.builder()
                .license(licenseEntity)
                .build();

        portfolioLicenseEntityRepository.save(portfolioLicenseEntity);

        portfolioEntity.addLicense(portfolioLicenseEntity);
        portfolioEntity = portfolioEntityRepository.save(portfolioEntity);

        memberEntity.setPortfolio(portfolioEntity);

        memberEntity.setUpdateAt(LocalDateTime.now());
        return memberEntityRepository.save(memberEntity);
    }

    @Override
    public MemberEntity subtractLicenseToMember(long memberId, License license) {
        MemberEntity memberEntity = this.findById(memberId);
        PortfolioEntity portfolioEntity = memberEntity.getPortfolio();

        if(portfolioEntity == null){
            return memberEntity;
        }

        Set<PortfolioLicenseEntity> licenseEntities = portfolioEntity.getLicenseList();
        if(licenseEntities == null){
            return memberEntity;
        }

        for(PortfolioLicenseEntity portfolioLicenseEntity : licenseEntities){
            if(portfolioLicenseEntity.isContain(license.getId())){
                portfolioEntity.removeLicense(portfolioLicenseEntity);
                portfolioLicenseEntityRepository.delete(portfolioLicenseEntity);
            }
        }

        memberEntity.setUpdateAt(LocalDateTime.now());
        return memberEntityRepository.save(memberEntity);
    }
}
