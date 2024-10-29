package com.wearwiz.domain.community;

import com.wearwiz.adapter.service.CommunityAdapter;
import com.wearwiz.adapter.service.RegisterLikeService;
import com.wearwiz.common.kafka.Like;
import com.wearwiz.domain.frommember.FromMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterCommunityLikeService implements RegisterLikeService {

    private final CommunityAdapter communityAdapter;
    @Override
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

        return communityEntity.getLikeCount();
    }
}
