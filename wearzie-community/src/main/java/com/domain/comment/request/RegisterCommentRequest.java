package com.domain.comment.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCommentRequest {

    @NotEmpty
    private String content;

    private long memberId;

    private long postId;
}
