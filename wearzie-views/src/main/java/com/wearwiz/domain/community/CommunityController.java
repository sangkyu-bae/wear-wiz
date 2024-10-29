package com.wearwiz.domain.community;

import com.wearwiz.adapter.factory.LikeFactory;
import com.wearwiz.adapter.service.RegisterLikeService;
import com.wearwiz.common.kafka.Like;
import com.wearwiz.common.kafka.LikeTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommunityController {

    private final LikeFactory likeFactory;

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
}
