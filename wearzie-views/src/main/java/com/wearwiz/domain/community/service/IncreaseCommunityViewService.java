package com.wearwiz.domain.community.service;

import com.wearwiz.adapter.service.CommunityAdapter;
import com.wearwiz.adapter.service.IncreaseViewService;
import com.wearwiz.common.kafka.IncreaseViewRequest;
import com.wearwiz.domain.community.Community;
import com.wearwiz.domain.frommember.FromMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncreaseCommunityViewService implements IncreaseViewService {

    private final CommunityAdapter communityViewAdapter;


    public void increaseView(IncreaseViewRequest request){

        FromMember fromMember = FromMember.createGenerate(null, request.getFromMemberId());
        List<FromMember> fromMembers = new ArrayList<>();
        fromMembers.add(fromMember);

        Community communityView = Community.createGenerate(
                request.getPostId(),
                0,
                0,
                fromMembers,
                null
        );

        communityViewAdapter.increaseCommunityView(communityView);
    }

}
