package com.adaptor.mapper;

import com.domain.comment.Comment;
import com.domain.comment.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentMapper {
    public Comment mapToDomain(CommentEntity comment){
        return Comment.createGenerateComment(
                comment.getCommentId(),
                comment.getContent(),
                comment.getMemberId(),
                comment.getCreateAt(),
                comment.getUpdateAt()
        );
    }
}
