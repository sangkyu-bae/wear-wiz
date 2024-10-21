package com.wearwiz.domain.post;

import com.wearwiz.domain.comment.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wearwiz.common.EnumClassMapperValue;
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
//    private final int codyType;
    private final EnumClassMapperValue communityType;

    private final EnumClassMapperValue itemType;

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
            EnumClassMapperValue communityType,
            EnumClassMapperValue itemEnum,
            String content,
            long memberId,
            LocalDateTime createAt,
            LocalDateTime updateAt,
            int views,
            List<Comment> commentList
    ){
        return new Post(postId,title,communityType,itemEnum,content,memberId,createAt,updateAt,views,commentList);
    }

}
