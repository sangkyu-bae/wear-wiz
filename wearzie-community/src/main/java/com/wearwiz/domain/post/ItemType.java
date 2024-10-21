package com.wearwiz.domain.post;

import com.wearwiz.common.EnumMapperType;

import java.util.Arrays;

public enum ItemType implements EnumMapperType {
    TOP(1,"상의"),
    BOTTOM(2,"하의"),
    OUTER(3,"아우터"),
    SHOES(4,"신발"),
    ACCESSORIES(5,"악세서리"),
    ETC(6,"기타")
    ;

    private final int value;
    private final String name;



    ItemType(int value, String name){
        this.value = value;
        this.name = name;
    }
    @Override
    public int getStatus() {
        return value;
    }

    @Override
    public String getName() {
        return name;
    }

    public static ItemType findItem(int value){
        return Arrays.stream(ItemType.values())
                .filter(item -> item.getStatus() == value)
                .findFirst()
                .orElseThrow();
    }
}
