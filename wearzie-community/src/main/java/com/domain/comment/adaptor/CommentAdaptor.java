package com.domain.comment.adaptor;


import com.domain.comment.Comment;
import com.domain.comment.CommentEntity;
import com.domain.comment.repository.CommentRepository;
import com.domain.post.PostEntity;
import com.domain.post.repository.PostRepository;
import com.wearwiz.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@PersistenceAdapter
@Slf4j
@RequiredArgsConstructor
public class CommentAdaptor {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    public CommentEntity registerComment(Comment comment, long postId){

        PostEntity postEntity = postRepository.findById(postId).orElseThrow(()->new IllegalArgumentException());

        CommentEntity commentEntity = CommentEntity.builder()
                .content(comment.getContent())
                .memberId(comment.getMemberId())
                .createAt(comment.getCreateAt())
                .updateAt(comment.getCreateAt())
                .build();

        postEntity.addComment(commentEntity);

        return commentRepository.save(commentEntity);
    }

}
