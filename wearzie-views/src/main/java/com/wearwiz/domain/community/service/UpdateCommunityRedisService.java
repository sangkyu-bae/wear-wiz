package com.wearwiz.domain.community.service;

import com.wearwiz.adapter.service.RedisService;
import com.wearwiz.domain.community.RedisCommunity;
import com.wearwiz.domain.community.RedisCommunityEntity;
import com.wearwiz.domain.community.repositroy.RedisCommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdateCommunityRedisService {

    private final RedisCommunityRepository redisCommunityRepository;

    private final StringRedisTemplate redisTemplate;



    private final RedisService redisService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateCommunity(RedisCommunity redisCommunity,String key){
         redisCommunityRepository.findById(String.valueOf(redisCommunity.getCommunityId()))
                .ifPresentOrElse(
                    findCommunity->{
                        int value = redisService.findCountByKey(key,redisCommunity.getCommunityId());
                        redisService.incrementValue(key,redisCommunity.getCommunityId(),value+1);
                    },
                        ()->{
                                RedisCommunityEntity communityEntity = RedisCommunityEntity.builder()
                                        .communityId(redisCommunity.getCommunityId())
                                        .communityTitle(redisCommunity.getCommunityTitle())
                                        .viewCount(redisCommunity.getViewCount())
                                        .likeCount(redisCommunity.getLikeCount())
                                        .build();

                                redisCommunityRepository.save(communityEntity);

                                redisService.incrementValue(key, redisCommunity.getCommunityId(), 1);
                        }
                );
    }

}

