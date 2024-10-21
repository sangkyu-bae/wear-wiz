package com.wearwiz.domain.post.repository;

import com.wearwiz.domain.post.PostEntity;

import java.util.Optional;

public interface PostRepositoryExtension {

    Optional <PostEntity> findWithCommentById(long id);
}
