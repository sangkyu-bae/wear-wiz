package com.wearwiz.domain.community.view.service;

import com.wearwiz.adapter.service.CommunityViewAdapter;
import com.wearwiz.adapter.service.IncreaseViewService;
import com.wearwiz.common.kafka.IncreaseViewRequest;
import com.wearwiz.domain.community.view.CommunityView;
import com.wearwiz.domain.frommember.FromMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncreaseCommunityViewService implements IncreaseViewService {

    private final CommunityViewAdapter communityViewAdapter;


    public void increaseView(IncreaseViewRequest request){

        FromMember fromMember = FromMember.createGenerate(null, request.getFromMemberId());
        List<FromMember> fromMembers = new ArrayList<>();
        fromMembers.add(fromMember);

        CommunityView communityView = CommunityView.createGenerate(
                request.getPostId(),
                0,
                fromMembers
        );

        communityViewAdapter.increaseCommunityView(communityView);
    }

}
