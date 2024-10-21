package com.wearwiz.domain.post.service;

import com.wearwiz.adaptor.mapper.PostMapper;
import com.wearwiz.domain.post.CommunityType;
import com.wearwiz.domain.post.ItemType;
import com.wearwiz.domain.post.Post;
import com.wearwiz.domain.post.PostEntity;
import com.wearwiz.adaptor.PostAdaptor;
import com.wearwiz.domain.post.request.RegisterPostRequest;
import com.wearwiz.common.EnumClassMapperValue;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostRegisterService {

    private final PostAdaptor postAdaptor;

    private final PostMapper postMapper;

    @Transactional
    public Post registerPost(RegisterPostRequest request){

        ItemType itemEnum = ItemType.findItem(request.getItemType());
        EnumClassMapperValue item = new EnumClassMapperValue(itemEnum);

        CommunityType communityType = CommunityType.findCommunity(request.getCodyType());
        EnumClassMapperValue community = new EnumClassMapperValue(communityType);
        Post createPost = Post.createGeneratePost(
                null,
                request.getTitle(),
                community,
                item,
                request.getContent(),
                request.getMemberId(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                0,
                new ArrayList<>()
        );

        PostEntity createPostEntity = postAdaptor.registerPost(createPost);

        return postMapper.mapToDomain(createPostEntity,false);
    }
}
