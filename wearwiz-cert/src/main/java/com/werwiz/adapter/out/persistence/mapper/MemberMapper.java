package com.werwiz.adapter.out.persistence.mapper;

import com.werwiz.adapter.out.persistence.entity.MemberEntity;
import com.werwiz.adapter.out.persistence.entity.PortfolioEntity;
import com.werwiz.domain.Category;
import com.werwiz.domain.Member;
import com.werwiz.domain.Portfolio;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberMapper {
    private final CategoryMapper categoryMapper;

    private final PortfolioMapper portfolioMapper;
    public Member mapToDomain(MemberEntity memberEntity){
        List<Category> categories = null;
        Portfolio portfolio = null;

        if(memberEntity.getCategory() != null){
            categories = new ArrayList<>();
            categories = memberEntity.getCategory().stream()
                    .map(category -> categoryMapper.mapToDomain(category.getCategory()))
                    .collect(Collectors.toList());
        }


        if(memberEntity.getPortfolio() != null){
            portfolio = portfolioMapper.mapToDomain(memberEntity.getPortfolio());
        }

       return  Member.createMember(
                memberEntity.getMemberId(),
                memberEntity.getEmail(),
                memberEntity.getArea(),
                memberEntity.getCounsel(),
                memberEntity.getRate(),
                memberEntity.getNickName(),
                memberEntity.getIntroduce(),
               categories,
                null,
                portfolio,
                memberEntity.getCreateAt(),
                memberEntity.getUpdateAt(),
                memberEntity.getName(),
                memberEntity.getPassword()
        );
    }


}
