package com.wearwiz.domain.community;

import com.wearwiz.adapter.factory.LikeFactory;
import com.wearwiz.adapter.service.RegisterLikeService;
import com.wearwiz.common.kafka.Like;
import com.wearwiz.common.kafka.LikeTypeEnum;
import com.wearwiz.domain.community.service.FindCommunityViewService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommunityController {

    private final LikeFactory likeFactory;

    private final FindCommunityViewService findCommunityViewService;

    @Value("${redis.community.like}")
    private String COMMUNITY_LIKE_KEY;

    @PostMapping("/view/v2/like/community/{postId}")
    public ResponseEntity<Integer> increaseLike(@PathVariable("postId")long postId,
                                          @RequestHeader("userId") long userId) throws IllegalAccessException {

        RegisterLikeService service = likeFactory.createRegisterLikeService(LikeTypeEnum.COMMUNITY_LIKE);

        Like likeRequest = Like.builder()
                .fromMemberId(userId)
                .toId(postId)
                .build();
        int likeCnt = service.registerLike(likeRequest);

        return ResponseEntity.ok().body(likeCnt);
    }

    @GetMapping("/view/v1/like/community/{limit}")
    public ResponseEntity<List<RedisCommunity>> findLikeRank(@PathVariable("limit") int limit){

        List<RedisCommunity> redisCommunities = findCommunityViewService.findCommunityByLimitAndKey(limit,COMMUNITY_LIKE_KEY);

        return ResponseEntity.ok().body(redisCommunities);
    }

    @GetMapping("/view/v1/view/community/{limit}")
    public ResponseEntity<List<RedisCommunity>> findViewRank(@PathVariable("limit") int limit){

        List<RedisCommunity> redisCommunities = findCommunityViewService.findCommunityByLimitAndKey(limit,"view");

        return ResponseEntity.ok().body(redisCommunities);
    }

}
