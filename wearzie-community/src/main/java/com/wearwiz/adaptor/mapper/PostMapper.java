package com.wearwiz.adaptor.mapper;

import com.wearwiz.domain.comment.Comment;
import com.wearwiz.domain.post.*;
import com.wearwiz.common.EnumClassMapperValue;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final CommentMapper commentMapper;

    public Post mapToDomain(PostEntity postEntity, boolean isLazy){
        List<Comment> commentList = new ArrayList<>();
        if(!isLazy){
            if(postEntity.getCommentList() != null){
                commentList = postEntity.getCommentList()
                        .stream().map(comment->commentMapper.mapToDomain(comment))
                        .collect(Collectors.toList());
            }
        }


        ItemType itemEnum = ItemType.findItem(postEntity.getItemType());
        EnumClassMapperValue item = new EnumClassMapperValue(itemEnum);

        CommunityType communityType = CommunityType.findCommunity(postEntity.getItemType());
        EnumClassMapperValue community = new EnumClassMapperValue(communityType);

        return Post.createGeneratePost(
                postEntity.getPostId(),
                postEntity.getTitle(),
                community,
                item,
                postEntity.getContent(),
                postEntity.getMemberId(),
                postEntity.getCreatAt(),
                postEntity.getUpdateAt(),
                postEntity.getViews(),
                commentList
        );
    }

    public SearchPost mapToDomain(Page<PostEntity> pagingPost){

        List<Post> postList = pagingPost.stream()
                .map(post -> this.mapToDomain(post,true))
                .collect(Collectors.toList());

        return SearchPost.createGenerate(
                postList,
                pagingPost.getNumber(),
                pagingPost.getSize(),
                pagingPost.getTotalElements(),
                pagingPost.getTotalPages()
        );

    }
}
