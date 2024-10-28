package com.wearwiz.adaptor;

import com.wearwiz.adaptor.out.kafka.ViewProducer;
import com.wearwiz.common.kafka.IncreaseViewRequest;
import com.wearwiz.common.kafka.ViewTypeEnum;
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

    private final ViewProducer viewProducer;

    public PostEntity registerPost(Post createPost){
        PostEntity postEntity = PostEntity.builder()
                .title(createPost.getTitle())
                .itemType(createPost.getItemType().getId())
                .communityType(createPost.getCommunityType().getId())
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

    /**
     * ToDO: kafka 메세지 트랜잭션과
     * 안묶이도록 aop 구현 예정
     * */
    public PostEntity findPostById(long postId,long memberId){
        PostEntity postEntity = this.findPostById(postId);


        IncreaseViewRequest request = IncreaseViewRequest.builder()
                .postId(postId)
                .fromMemberId(memberId)
                .viewTypeEnum(ViewTypeEnum.COMMUNITY)
                .build();

        viewProducer.sendViewIncrease(request);
        return postEntity;
    }
    public Page<PostEntity> findPagingByType(Pageable pageable,int itemType){
        return postRepository.findByItemType(pageable,itemType);
    }

    public Page<PostEntity> findPagingByTCommunityType(Pageable pageable,int itemType){
        return postRepository.findByCommunityType(pageable,itemType);
    }
}
