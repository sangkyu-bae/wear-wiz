package com.wearwiz.common.kafka;

import com.wearwiz.common.EnumMapperType;

public enum LikeTypeEnum implements EnumMapperType {
     VIEW(1,"조회수"),
    COMMUNITY_LIKE(1,"RegisterCommunityLikeService"),
    COMMENT_LIKE(2,"댓글 좋아요"),
    OOTD_LIKE(3,"OOTD 좋아요"),
    PORTFOLIO_LIKE(4,"포트폴리오 좋아요")
    ;

    private final int value;
    private final String serviceName;
    LikeTypeEnum(int value, String name){
        this.value = value;
        this.serviceName = name;
    }

    @Override
    public int getStatus() {
        return value;
    }

    @Override
    public String getName() {
        return serviceName;
    }
}
