package com.wearwiz.domain.community.view;

import com.wearwiz.domain.frommember.FromMember;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.Set;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CommunityView {

    private final long postId;

    private final int viewCount;

    private final List<FromMember> fromMembers;

    public static CommunityView createGenerate(
            long postId,
            int viewCount,
            List<FromMember> fromMembers
    ){
        return new CommunityView(postId,viewCount,fromMembers);
    }
}
