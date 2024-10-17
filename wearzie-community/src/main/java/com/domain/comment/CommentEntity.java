package com.domain.comment;

import com.domain.post.PostEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="commentId")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_comment") @Builder
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    private String content;

    private long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    private PostEntity post;

}
