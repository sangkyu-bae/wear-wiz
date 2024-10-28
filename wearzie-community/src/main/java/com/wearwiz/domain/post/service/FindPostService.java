package com.wearwiz.domain.post.service;

import com.wearwiz.adaptor.mapper.PostMapper;
import com.wearwiz.domain.post.Post;
import com.wearwiz.domain.post.PostEntity;
import com.wearwiz.adaptor.PostAdaptor;
import com.wearwiz.domain.post.SearchPost;
import com.wearwiz.domain.post.request.FindPagingPostRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class FindPostService {


    private final PostAdaptor postAdaptor;

    private final PostMapper postMapper;
    public Post findPostById(long postId){
        PostEntity findEntity = postAdaptor.findPostById(postId);

        return postMapper.mapToDomain(findEntity,false);
    }

    public Post findPostById(long postId,long userId){
        PostEntity findEntity = postAdaptor.findPostById(postId,userId);

        return postMapper.mapToDomain(findEntity,false);
    }

    public SearchPost findPagingPostByType(FindPagingPostRequest request){
        Pageable pageable = PageRequest.of(request.getPageNum() - 1, request.getPageSize(), Sort.Direction.ASC,"postId");
        Page<PostEntity> postEntityPage = postAdaptor.findPagingByType(pageable, request.getType());

        return postMapper.mapToDomain(postEntityPage);
    }

    public SearchPost findPagingPostByCommunityType(FindPagingPostRequest request){
        Pageable pageable = PageRequest.of(request.getPageNum() - 1, request.getPageSize(), Sort.Direction.ASC,"postId");
        Page<PostEntity> postEntityPage = postAdaptor.findPagingByTCommunityType(pageable, request.getType());

        return postMapper.mapToDomain(postEntityPage);
    }
}
