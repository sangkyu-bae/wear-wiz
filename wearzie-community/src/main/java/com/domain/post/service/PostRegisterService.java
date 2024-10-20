package com.domain.post.service;

import com.adaptor.mapper.PostMapper;
import com.domain.post.Post;
import com.domain.post.PostEntity;
import com.domain.post.adaptor.PostAdaptor;
import com.domain.post.request.RegisterPostRequest;
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

        Post createPost = Post.createGeneratePost(
                null,
                request.getTitle(),
                request.getCodyType(),
                request.getItemType(),
                request.getContent(),
                request.getMemberId(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                0,
                new ArrayList<>()
        );

        PostEntity createPostEntity = postAdaptor.RegisterPost(createPost);

        return postMapper.mapToDomain(createPostEntity);
    }
}
