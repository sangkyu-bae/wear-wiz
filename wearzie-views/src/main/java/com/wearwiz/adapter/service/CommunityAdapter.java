package com.wearwiz.adapter.service;

import com.wearwiz.adapter.entity.FromMemberCommunityEntity;
import com.wearwiz.adapter.entity.FromMemberViewRepository;
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

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class CommunityAdapter {

    private final CommunityViewRepository communityViewRepository;

    private final FromMemberRepository fromMemberRepository;

    private final FromMemberViewRepository fromMemberViewRepository;
    @Transactional
    public CommunityEntity increaseCommunityView(Community communityView) {
        FromMember fromMember = communityView.getFromViewMembers().get(0);

        com.wearwiz.domain.frommember.FromMemberEntity findFromMember = fromMemberRepository.findById(fromMember.getFromMemberId())
                .orElseGet(() -> newFromMember(fromMember));

        CommunityEntity communityViewEntity = communityViewRepository.findById(communityView.getPostId())
                .orElseGet(()->newCommunityView(communityView));

        FromMemberCommunityEntity fromMemberEntity = FromMemberCommunityEntity.builder()
                .viewFromMember(findFromMember)
                .communityView(communityViewEntity)
                .build();

        try{
            FromMemberCommunityEntity saveFromMemberView = fromMemberViewRepository.save(fromMemberEntity);
        }catch (Exception e){
            // sql 익셉션 -> 멀티키시 다시 커스텀 오류 필요
            return null;
        }

        communityViewEntity.increaseViewCount();

        return communityViewEntity;
    }

    private com.wearwiz.domain.frommember.FromMemberEntity newFromMember(FromMember fromMember){
        com.wearwiz.domain.frommember.FromMemberEntity fromMemberEntity = com.wearwiz.domain.frommember.FromMemberEntity.builder()
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

    @Transactional
    public CommunityEntity increaseCommunityLike(Community community){
        FromMember fromLikeMember = community.getFromLikeMembers().get(0);

       FromMemberEntity fromLikeMemberEntity = fromMemberRepository.findById(fromLikeMember.getFromMemberId())
                .orElseGet(() -> newFromMember(fromLikeMember));

        CommunityEntity communityViewEntity = communityViewRepository.findById(community.getPostId())
                .orElseGet(()->newCommunityView(community));



        FromMemberCommunityEntity fromMemberEntity = FromMemberCommunityEntity.builder()
                .likeFromMember(fromLikeMemberEntity)
                .communityView(communityViewEntity)
                .build();

        try{
            FromMemberCommunityEntity saveFromMemberView = fromMemberViewRepository.save(fromMemberEntity);
            fromLikeMemberEntity.addFromMemberLike(saveFromMemberView);

        }catch (Exception e){
            // sql 익셉션 -> 멀티키시 다시 커스텀 오류 필요
            return null;
        }


        communityViewEntity.increaseLikeCount();

        return communityViewEntity;
    }
}
