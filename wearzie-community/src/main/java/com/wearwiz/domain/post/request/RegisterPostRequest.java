package com.wearwiz.domain.post.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegisterPostRequest {

    @NotEmpty
    @Size(min = 2,max = 20)
    private String title;

    @NotNull
    private Integer codyType;

    @NotNull
    private Integer itemType;

    @NotEmpty
    @Size(min = 2)
    private String content;

    private Long memberId;
}
