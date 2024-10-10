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

//    private PortfolioEntity setPortfolio(Portfolio portfolio,PortfolioEntity entity){
//
//        Set<PortfolioLicenseEntity> portfolioLicense = entity.getLicenseList();
//        Map<Long, Boolean> licenseMap= entity.getLicenseList().stream()
//                .collect(Collectors.toMap(
//                        license -> license.getLicense().getLicenseId(),
//                        license -> false
//                ));
//
//
//        if(!portfolio.getLicenses().isEmpty()){
//
//            for(License license : portfolio.getLicenses()){
//
//                if(licenseMap.containsKey(license.getId())){
//                    licenseMap.put(license.getId(),true);
//                    continue;
//                }
//
//                LicenseEntity licenseEntity = modelMapper.map(license,LicenseEntity.class);
//                licenseEntity.setLicenseId(license.getId());
//
//                PortfolioLicenseEntity portfolioLicenseEntity = PortfolioLicenseEntity.builder()
//                        .license(licenseEntity)
//                        .build();
//
//                portfolioLicense.add(portfolioLicenseEntity);
//            }
//
//            for(Long key : licenseMap.keySet()){
//                boolean isContain = licenseMap.get(key);
//
//                if(!isContain){
//                    portfolioLicense.
//                }
//            }
//        }
//
//        if(portfolio.getImages() != null){
//
//
//        }
//
//        PortfolioEntity portfolioEntity = PortfolioEntity.builder()
//                .licenseList(portfolioLicense)
//                .build();
//
//        return portfolioEntity;
//    }

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

    @Override
    public MemberEntity updateMember(Member updateMember) {
        MemberEntity memberEntity = this.findById(updateMember.getMemberId());

        if(updateMember.getArea() != null){
            memberEntity.setArea(updateMember.getArea());
        }

        if(updateMember.getIntroduce() != null){
            memberEntity.setIntroduce(updateMember.getIntroduce());
        }

//        if(updateMember.getPortfolio() != null){
//            Portfolio portfolio = updateMember.getPortfolio();
//
//            PortfolioEntity portfolioEntity = memberEntity.getPortfolio();
//
//            Set<PortfolioLicenseEntity> licenseEntityList = null;
//            if(portfolio.getLicenses() != null){
//                licenseEntityList = new HashSet<>();
//
//
//                for(License license : portfolio.getLicenses()){
//                    LicenseEntity licenseEntity = LicenseEntity.builder()
//                            .licenseId(license.getId())
//                            .name(license.getName())
//                            .build();
//
//                    licenseEntityList.add(
//                            PortfolioLicenseEntity.builder()
//                                    .isUse(true)
//                                    .license(licenseEntity)
//                                    .build()
//                    );
//                }
//
//
//            }
//
//
//            Set<ImageEntity> imageEntityList = null;
//            if(portfolio.getImages() != null){
//                imageEntityList = new HashSet<>();
//
//                imageEntityList = portfolio.getImages().stream()
//                        .map(image -> new ImageEntity(image.getId(),image.getFilePath(),null))
//                        .collect(Collectors.toSet());
//            }
//
//            if(portfolioEntity == null){
//                portfolioEntity = PortfolioEntity.builder()
//                        .licenseList(licenseEntityList)
//                        .imageList(imageEntityList)
//                        .build();
//
//                List<PortfolioLicenseEntity> saveLicenseEntity = portfolioLicenseEntityRepository.saveAll(licenseEntityList);
//                portfolioEntity = portfolioEntityRepository.save(portfolioEntity);
//
//                portfolioEntity.addLicense(saveLicenseEntity);
//                portfolioEntity.addImage(imageEntityList);
//
//                memberEntity.setPortfolio(portfolioEntity);
//
//                return memberEntityRepository.save(memberEntity);
//            }
//
//            Set<PortfolioLicenseEntity> licenseEntities = portfolioEntity.getLicenseList();
//            if(licenseEntities != null){
//
//            }
//        }

        return memberEntityRepository.save(memberEntity);
    }
}
