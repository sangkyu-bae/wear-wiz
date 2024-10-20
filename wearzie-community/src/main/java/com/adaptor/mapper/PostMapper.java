package com.adaptor.mapper;

import com.domain.comment.Comment;
import com.domain.post.Post;
import com.domain.post.PostEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostMapper {

    private final CommentMapper commentMapper;

    public Post mapToDomain(PostEntity postEntity){

        List<Comment> commentList = postEntity.getCommentList()
                .stream().map(comment->commentMapper.mapToDomain(comment))
                .collect(Collectors.toList());

        return Post.createGeneratePost(
                postEntity.getPostId(),
                postEntity.getTitle(),
                postEntity.getCodyType(),
                postEntity.getItemType(),
                postEntity.getContent(),
                postEntity.getMemberId(),
                postEntity.getCreatAt(),
                postEntity.getUpdateAt(),
                postEntity.getViews(),
                commentList
        );
    }
}
