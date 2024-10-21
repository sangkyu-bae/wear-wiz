package com.wearwiz.domain.comment;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Comment {

    private final Long commentId;

    private final String content;

    private final long memberId;

    private final LocalDateTime createAt;

    private final LocalDateTime updateAt;

    public static Comment createGenerateComment(
            Long commentId,
            String content,
            long memberId,
            LocalDateTime createAt,
            LocalDateTime updateAt
    ){
        return new Comment(commentId,content,memberId,createAt,updateAt);
    }
}
