package com.wearwiz.domain.post.repository;

import com.wearwiz.domain.post.Post;
import com.wearwiz.domain.post.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long>, PostRepositoryExtension {

    Page<PostEntity> findByItemType(Pageable pageable,int itemType);
}
