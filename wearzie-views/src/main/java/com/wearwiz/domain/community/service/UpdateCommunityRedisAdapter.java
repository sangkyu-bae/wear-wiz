package com.wearwiz.domain.community.service;
import com.wearwiz.adapter.service.RedisSortedService;
import com.wearwiz.common.PersistenceAdapter;
import com.wearwiz.domain.community.RedisCommunity;
import com.wearwiz.domain.community.RedisCommunityEntity;
import com.wearwiz.domain.community.repositroy.RedisCommunityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class UpdateCommunityRedisAdapter {

    private final RedisCommunityRepository redisCommunityRepository;
    private final RedisSortedService redisSortedService;
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void execute(RedisCommunity redisCommunity, String key){
        redisCommunityRepository.findById(String.valueOf(redisCommunity.getCommunityId()))
                .ifPresentOrElse(
                        existingCommunity -> updateScoreInRedis(key, redisCommunity.getCommunityId()),
                        () -> saveNewCommunityInRedis(redisCommunity, key)
                );
    }

    private void updateScoreInRedis( String key, long communityId) {
        int currentScore = redisSortedService.findByKey(key, communityId);
        if(currentScore == 0){
            redisSortedService.register(key, communityId, 1);
        }else{
            redisSortedService.updateScore(key, communityId, 1);
        }
    }

    private void saveNewCommunityInRedis(RedisCommunity redisCommunity, String key) {
        RedisCommunityEntity communityEntity = RedisCommunityEntity.builder()
                .communityId(redisCommunity.getCommunityId())
                .communityTitle(redisCommunity.getCommunityTitle())
                .viewCount(redisCommunity.getViewCount())
                .likeCount(redisCommunity.getLikeCount())
                .build();

        redisCommunityRepository.save(communityEntity);
        redisSortedService.register(key, redisCommunity.getCommunityId(), 1);
    }

}

