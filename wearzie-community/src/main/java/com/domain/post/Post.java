package com.domain.post;

import com.domain.comment.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Post {

    private final Long postId;

    private final String title;
    //enum 변경예정
    private final int codyType;
    //enum 변경예정
    private final int itemType;

    private final String content;

    private final long memberId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;

    private int views;

    private List<Comment> commentList;

    public static Post createGeneratePost(
            Long postId,
            String title,
            int codyType,
            int itemType,
            String content,
            long memberId,
            LocalDateTime createAt,
            LocalDateTime updateAt,
            int views,
            List<Comment> commentList
    ){
        return new Post(postId,title,codyType,itemType,content,memberId,createAt,updateAt,views,commentList);
    }

}
