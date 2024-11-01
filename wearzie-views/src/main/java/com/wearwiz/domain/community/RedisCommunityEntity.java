package com.wearwiz.domain.community;


import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@RedisHash("community")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RedisCommunityEntity {

    @Id
    private String Id;

    private String communityTitle;

    private long communityId;

    private int viewCount;

    private int likeCount;
}
