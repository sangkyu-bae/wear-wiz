package com.werwiz.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Image {

    private final Long id;

    private final String filePath;

    public static Image createImage(Long id, String name){
        return new Image(id,name);
    }
}
