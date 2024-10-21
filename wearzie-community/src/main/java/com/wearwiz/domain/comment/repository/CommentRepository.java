package com.wearwiz.domain.comment.repository;

import com.wearwiz.domain.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}
