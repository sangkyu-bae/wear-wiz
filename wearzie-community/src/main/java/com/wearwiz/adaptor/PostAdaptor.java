package com.wearwiz.adaptor;

import com.wearwiz.domain.post.Post;
import com.wearwiz.domain.post.PostEntity;
import com.wearwiz.domain.post.repository.PostRepository;
import com.wearwiz.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@RequiredArgsConstructor
@PersistenceAdapter
@Slf4j
public class PostAdaptor {

    private final PostRepository postRepository;

    public PostEntity registerPost(Post createPost){
        PostEntity postEntity = PostEntity.builder()
                .title(createPost.getTitle())
                .itemType(createPost.getItemType().getId())
                .codyType(createPost.getCommunityType().getId())
                .content(createPost.getContent())
                .memberId(createPost.getMemberId())
                .creatAt(createPost.getCreateAt())
                .updateAt(createPost.getUpdateAt())
                .build();

        return postRepository.save(postEntity);

    }

    public PostEntity findPostById(long postId){
        return postRepository.findWithCommentById(postId)
                .orElseThrow(()->new IllegalArgumentException());
    }

    public Page<PostEntity> findPagingByType(Pageable pageable,int itemType){
        return postRepository.findByItemType(pageable,itemType);
    }
}
