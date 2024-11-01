package com.wearwiz.domain.community;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RedisCommunity {

    private long communityId;

    private String communityTitle;

    private int viewCount;

    private int likeCount;

    public static RedisCommunity generateCreate(long communityId,String communityTitle,int viewCount,int likeCount){
        return new RedisCommunity(communityId,communityTitle,viewCount,likeCount);
    }
}
