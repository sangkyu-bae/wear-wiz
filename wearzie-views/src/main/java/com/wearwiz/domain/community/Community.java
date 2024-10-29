package com.wearwiz.domain.community;

import com.wearwiz.domain.frommember.FromMember;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Community {

    private final long postId;

    private final int viewCount;

    private final int likeCount;

    private final List<FromMember> fromViewMembers;

    private final List<FromMember> fromLikeMembers;

    public static Community createGenerate(
            long postId,
            int viewCount,
            int likeCount,
            List<FromMember> fromViewMembers,
            List<FromMember> fromLikeMembers
    ){
        return new Community(postId,viewCount,likeCount,fromViewMembers,fromLikeMembers);
    }
}
