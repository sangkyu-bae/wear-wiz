package com.werwiz.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Category {

    private final Long id;

    private final String name;

    public static Category createCategory(Long id, String name){
        return new Category(id,name);
    }
}
