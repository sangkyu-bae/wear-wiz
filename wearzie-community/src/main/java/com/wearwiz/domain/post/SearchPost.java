package com.wearwiz.domain.post;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SearchPost {

    private final List<Post> postList;

    private final int pageNumber;

    private final int pageSize;

    private final long totalElement;

    private final int totalPage;

    public static SearchPost createGenerate(
            List<Post> postList,
            int pageNumber,
            int pageSize,
            long totalElement,
            int totalPage
    ){
        return new SearchPost(
                postList,
                pageNumber,
                pageSize,
                totalElement,
                totalPage
        );
    }
}
