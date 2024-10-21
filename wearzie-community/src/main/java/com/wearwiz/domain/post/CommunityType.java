package com.wearwiz.domain.post;

import com.wearwiz.common.EnumMapperType;

import java.util.Arrays;

public enum CommunityType implements EnumMapperType {
    CASUAL(1,"캐주얼"),
    MINIMAL(2,"미니멀"),
    STREET(3,"스트릿"),
    CITY_BOY(4,"시티보이"),
    GOFF_CORE(5,"고프코어")
    ;

    private final int type;
    private final String name;

    CommunityType(int type, String name){
        this.type = type;
        this.name = name;
    }
    @Override
    public int getStatus() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public static CommunityType findCommunity(int value){
        return Arrays.stream(CommunityType.values())
                .filter(item -> item.getStatus() == value)
                .findFirst()
                .orElseThrow();
    }
}
