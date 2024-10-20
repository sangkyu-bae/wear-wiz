package com.domain.post;

import com.domain.comment.CommentEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of="postId")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_post") @Builder
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    private String title;

    private int codyType;

    private int itemType;

    @Lob
    private String content;

    private long memberId;

    private LocalDateTime creatAt;

    private LocalDateTime updateAt;

    private int views;

    @OneToMany(mappedBy = "post",fetch = FetchType.LAZY)
    private Set<CommentEntity> commentList;

    public void addComment(CommentEntity commentEntity){
        commentEntity.setPost(this);

        if(commentList == null){
            commentList = new HashSet<>();
        }

        commentList.add(commentEntity);
    }
}
