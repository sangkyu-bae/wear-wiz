package com.domain.comment.service;

import com.adaptor.mapper.CommentMapper;
import com.domain.comment.Comment;
import com.domain.comment.CommentEntity;
import com.domain.comment.adaptor.CommentAdaptor;
import com.domain.comment.request.RegisterCommentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class RegisterCommentService {

    private final CommentAdaptor commentAdaptor;

    private final CommentMapper commentMapper;

    @Transactional
    public Comment registerComment(RegisterCommentRequest request,long postId){

        Comment createComment = Comment.createGenerateComment(
                null,
                request.getContent(),
                request.getMemberId(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );

        CommentEntity createCommentEntity =  commentAdaptor.registerComment(createComment,postId);

        return commentMapper.mapToDomain(createCommentEntity);

    }
}
