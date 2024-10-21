package com.wearwiz.domain.post.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindPagingPostRequest {


    @NotNull
    private int pageNum;

    @NotNull
    private int type;

    @NotNull
    private int pageSize;

}
