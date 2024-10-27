package com.wearwiz.adapter.service;

import com.wearwiz.adapter.entity.FromMemberViewEntity;
import com.wearwiz.adapter.entity.FromMemberViewRepository;
import com.wearwiz.common.PersistenceAdapter;
import com.wearwiz.domain.community.like.CommunityLikeEntity;
import com.wearwiz.domain.community.view.CommunityView;
import com.wearwiz.domain.community.view.CommunityViewEntity;
import com.wearwiz.domain.community.view.repositroy.CommunityViewRepository;
import com.wearwiz.domain.frommember.FromMember;
import com.wearwiz.domain.frommember.FromMemberEntity;
import com.wearwiz.domain.frommember.repository.FromMemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class CommunityViewAdapter {

    private final CommunityViewRepository communityViewRepository;

    private final FromMemberRepository fromMemberRepository;

    private final FromMemberViewRepository fromMemberViewRepository;
    public CommunityViewEntity increaseCommunityView(CommunityView communityView) {
        FromMember fromMember = communityView.getFromMembers().get(0);

        FromMemberEntity findFromMember = fromMemberRepository.findById(fromMember.getId())
                .orElseGet(() -> newFromMember(fromMember));

        CommunityViewEntity communityViewEntity = communityViewRepository.findById(communityView.getPostId())
                .orElseGet(()->newCommunityView(communityView));

        FromMemberViewEntity fromMemberViewEntity = FromMemberViewEntity.builder()
                .build();

        FromMemberViewEntity saveFromMemberView = fromMemberViewRepository.save(fromMemberViewEntity);

        findFromMember.addFromMemberView(saveFromMemberView);
        communityViewEntity.addFromMemberView(saveFromMemberView);

        return communityViewEntity;
    }

    private FromMemberEntity newFromMember(FromMember fromMember){
        FromMemberEntity fromMemberEntity = FromMemberEntity.builder()
                .fromId(fromMember.getFromMemberId())
                .build();

        return fromMemberRepository.save(fromMemberEntity);
    }

    private CommunityViewEntity newCommunityView(CommunityView communityView){
        CommunityViewEntity communityViewEntity = CommunityViewEntity.builder()
                .communityViewId(communityView.getPostId())
                .build();

        return communityViewRepository.save(communityViewEntity);
    }
}
