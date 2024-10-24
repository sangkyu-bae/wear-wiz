package com.wearwiz.domain.post.repository;

import com.querydsl.jpa.JPQLQuery;
import com.wearwiz.domain.comment.QCommentEntity;
import com.wearwiz.domain.post.PostEntity;
import com.wearwiz.domain.post.QPostEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.Optional;

public class PostRepositoryExtensionImpl extends QuerydslRepositorySupport implements PostRepositoryExtension{

    public PostRepositoryExtensionImpl() {
        super(PostEntity.class);
    }

    QPostEntity qPostEntity = QPostEntity.postEntity;

    QCommentEntity qCommentEntity = QCommentEntity.commentEntity;

    @Override
    public Optional<PostEntity> findWithCommentById(long id) {

        JPQLQuery<PostEntity> query = from(qPostEntity)
                .leftJoin(qPostEntity.commentList, qCommentEntity).fetchJoin()  // 조인 경로 수정
                .where(qPostEntity.postId.eq(id))
                .distinct();

        return Optional.ofNullable(query.fetchOne());
    }
}
