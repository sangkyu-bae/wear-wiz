package com.werwiz.adapter.out.persistence;

import com.wearwiz.common.PersistenceAdapter;
import com.werwiz.adapter.out.persistence.entity.*;
import com.werwiz.adapter.out.persistence.repository.*;
import com.werwiz.application.port.out.JoinMemberPort;
import com.werwiz.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberAdapter implements JoinMemberPort {

    private final MemberEntityRepository memberEntityRepository;
    private final MemberCategoryEntityRepository memberCategoryEntityRepository;
    private final ImageEntityRepository imageEntityRepository;
    private final PortfolioLicenseEntityRepository portfolioLicenseEntityRepository;

    private final PortfolioEntityRepository portfolioEntityRepository;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;


    @Override
    public MemberEntity joinMember(Member member) {
        Set<MemberCategoryEntity> memberCategoryList = null;
        PortfolioEntity portfolio = null;
        if(!member.getCategorys().isEmpty()){
            memberCategoryList = new HashSet<>();
            for(Category category : member.getCategorys()){
                CategoryEntity categoryEntity = modelMapper.map(category,CategoryEntity.class);
                memberCategoryList.add(
                        MemberCategoryEntity.builder()
                                .category(categoryEntity)
                                .build()
                );
            }

            memberCategoryList = (Set<MemberCategoryEntity>) memberCategoryEntityRepository.saveAll(memberCategoryList);
        }

        if(member.getPortfolio() != null){
            portfolio = setPortfolio(member.getPortfolio());

            Set<ImageEntity> imageEntities = new HashSet<>();
            Set<PortfolioLicenseEntity> licenseEntities = new HashSet<>();

            if(portfolio.getImageList() != null){
                 imageEntities = (Set<ImageEntity>) imageEntityRepository.saveAll(portfolio.getImageList());
            }

            if(portfolio.getLicenseList() != null){
                licenseEntities = (Set<PortfolioLicenseEntity>) portfolioLicenseEntityRepository.saveAll(portfolio.getLicenseList());
            }

            portfolio = portfolioEntityRepository.save(portfolio);

            portfolio.addImage(imageEntities);
            portfolio.addLicense(licenseEntities);
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

        memberEntity.addCategory(memberCategoryList);

        return memberEntity;
    }

    private PortfolioEntity setPortfolio(Portfolio portfolio){
        Set<ImageEntity> images = null;
        Set<PortfolioLicenseEntity> portfolioLicense = null;
        if(!portfolio.getImages().isEmpty()){
            images = new HashSet<>();

            for(Image image : portfolio.getImages()){
                images.add(modelMapper.map(image,ImageEntity.class));
            }
        }

        if(!portfolio.getLicenses().isEmpty()){
            portfolioLicense = new HashSet<>();

            for(License license : portfolio.getLicenses()){

                PortfolioLicenseEntity portfolioLicenseEntity = PortfolioLicenseEntity.builder()
                        .license(modelMapper.map(license, LicenseEntity.class))
                        .build();
            }

        }

        PortfolioEntity portfolioEntity = PortfolioEntity.builder()
                .imageList(images)
                .licenseList(portfolioLicense)
                .build();

        return portfolioEntity;
    }
}
