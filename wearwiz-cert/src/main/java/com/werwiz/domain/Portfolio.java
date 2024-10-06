package com.werwiz.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Portfolio {

    private final List<Image> images;

    private final List<License> licenses;


    public static Portfolio createPortfolio(List<Image> images, List<License> licenses){
        return new Portfolio(images,licenses);
    }

}
