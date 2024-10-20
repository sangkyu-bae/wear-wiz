package com.domain.post.adaptor;

import com.domain.post.Post;
import com.domain.post.PostEntity;
import com.domain.post.repository.PostRepository;
import com.wearwiz.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@PersistenceAdapter
@Slf4j
public class PostAdaptor {

    private final PostRepository postRepository;

    public PostEntity RegisterPost(Post createPost){
        PostEntity postEntity = PostEntity.builder()
                .title(createPost.getTitle())
                .itemType(createPost.getItemType())
                .codyType(createPost.getCodyType())
                .content(createPost.getContent())
                .memberId(createPost.getMemberId())
                .creatAt(createPost.getCreateAt())
                .updateAt(createPost.getUpdateAt())
                .build();

        return postRepository.save(postEntity);

    }
}
