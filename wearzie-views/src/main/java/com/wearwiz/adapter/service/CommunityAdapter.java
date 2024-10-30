package com.wearwiz.adapter.service;

import com.wearwiz.adapter.entity.FromMemberCommunityEntity;
import com.wearwiz.adapter.entity.FromMemberCommunityRepository;
import com.wearwiz.common.PersistenceAdapter;
import com.wearwiz.domain.community.Community;
import com.wearwiz.domain.community.CommunityEntity;
import com.wearwiz.domain.community.repositroy.CommunityViewRepository;
import com.wearwiz.domain.frommember.FromMember;
import com.wearwiz.domain.frommember.FromMemberEntity;
import com.wearwiz.domain.frommember.repository.FromMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class CommunityAdapter {

    private final CommunityViewRepository communityViewRepository;

    private final FromMemberRepository fromMemberRepository;

    private final FromMemberCommunityRepository fromMemberCommunityRepository;
    @Transactional
    public CommunityEntity increaseCommunityView(Community communityView) {
        FromMember fromMember = communityView.getFromViewMembers().get(0);

        FromMemberEntity findFromMember = fromMemberRepository.findById(fromMember.getFromMemberId())
                .orElseGet(() -> newFromMember(fromMember));

        CommunityEntity communityViewEntity = communityViewRepository.findById(communityView.getPostId())
                .orElseGet(()->newCommunityView(communityView));

        if (!findFromMember.ifPresentByFromMemberCommunity()) {
            FromMemberCommunityEntity fromMemberEntity = FromMemberCommunityEntity.builder()
                    .viewFromMember(findFromMember)
                    .community(communityViewEntity)
                    .build();

            fromMemberCommunityRepository.save(fromMemberEntity);

            communityViewEntity.increaseViewCount();
        }

        if (Optional.ofNullable(findFromMember.getDetchView()).isPresent()) {
            return communityViewEntity;
        }

        Optional.ofNullable(findFromMember.getDetchLike()).ifPresent(detachedLikeCommunityEntity -> {
            detachedLikeCommunityEntity.setViewFromMember(findFromMember);
        });

        communityViewEntity.increaseViewCount();

        return communityViewEntity;
    }

    @Transactional
    public CommunityEntity increaseCommunityLike(Community community){
        FromMember fromLikeMember = community.getFromLikeMembers().get(0);

        FromMemberEntity fromLikeMemberEntity = fromMemberRepository.findById(fromLikeMember.getFromMemberId())
                .orElseGet(() -> newFromMember(fromLikeMember));

        CommunityEntity communityViewEntity = communityViewRepository.findById(community.getPostId())
                .orElseGet(()->newCommunityView(community));


        if (!fromLikeMemberEntity.ifPresentByFromMemberCommunity()) {
            FromMemberCommunityEntity fromMemberEntity = FromMemberCommunityEntity.builder()
                    .likeFromMember(fromLikeMemberEntity)
                    .community(communityViewEntity)
                    .build();

            FromMemberCommunityEntity saveFromMemberView = fromMemberCommunityRepository.save(fromMemberEntity);
            fromLikeMemberEntity.addFromMemberLike(saveFromMemberView);

            communityViewEntity.increaseLikeCount();

            return communityViewEntity;
        }

        if (Optional.ofNullable(fromLikeMemberEntity.getDetchLike()).isPresent()) {
            return communityViewEntity;
        }


        fromLikeMemberEntity.getDetchView().setLikeFromMember(fromLikeMemberEntity);
        communityViewEntity.increaseLikeCount();

        return communityViewEntity;
    }
    private FromMemberEntity newFromMember(FromMember fromMember){
        FromMemberEntity fromMemberEntity =FromMemberEntity.builder()
                .id(fromMember.getFromMemberId())
                .build();

        return fromMemberRepository.save(fromMemberEntity);
    }

    private CommunityEntity newCommunityView(Community communityView){
        CommunityEntity communityViewEntity = CommunityEntity.builder()
                .id(communityView.getPostId())
                .viewCount(0)
                .likeCount(0)
                .build();

        return communityViewRepository.save(communityViewEntity);
    }


}
