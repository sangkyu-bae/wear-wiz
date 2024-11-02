package com.wearwiz.adapter.mapper;

import com.wearwiz.domain.community.RedisCommunity;
import com.wearwiz.domain.community.RedisCommunityEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RedisCommunityMapper {

    public RedisCommunity mapToDomain(RedisCommunityEntity communityEntity){

        return RedisCommunity.generateCreate(
                communityEntity.getCommunityId(),
                communityEntity.getCommunityTitle(),
                communityEntity.getViewCount(),
                communityEntity.getLikeCount()
        );
    }
}
