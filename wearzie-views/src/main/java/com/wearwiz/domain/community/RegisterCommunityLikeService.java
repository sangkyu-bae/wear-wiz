package com.wearwiz.domain.community;

import com.wearwiz.adapter.service.CommunityAdapter;
import com.wearwiz.adapter.service.RegisterLikeService;
import com.wearwiz.common.kafka.Like;
import com.wearwiz.domain.community.service.UpdateCommunityRedisService;
import com.wearwiz.domain.frommember.FromMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterCommunityLikeService implements RegisterLikeService {

    private final CommunityAdapter communityAdapter;

    private final UpdateCommunityRedisService updateCommunityRedisService;

    @Value("${redis.community.like}")
    private String COMMUNITY_LIKE_KEY;
    @Override
    @Transactional
    public int registerLike(Like likeRequest) {

        List<FromMember> likeMembers = new ArrayList<>();

        likeMembers.add(FromMember.createGenerate(
                null,
                likeRequest.getFromMemberId()
        ));

        Community community = Community.createGenerate(likeRequest.getToId(),
                0,
                0,
                null,
                likeMembers
        );


        CommunityEntity communityEntity = communityAdapter.increaseCommunityLike(community);


        try{
            RedisCommunity redisCommunity = RedisCommunity.generateCreate(likeRequest.getToId(), likeRequest.getTitle(), 0,0);
            updateCommunityRedisService.updateCommunity(redisCommunity,COMMUNITY_LIKE_KEY);
        }catch (Exception e){
//            log.error(e.getStackTrace());
            e.printStackTrace();
        }
        return communityEntity.getLikeCount();
    }
}
