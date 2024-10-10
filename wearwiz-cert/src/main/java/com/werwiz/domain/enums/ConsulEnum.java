package com.werwiz.domain.enums;

import com.wearwiz.common.EnumMapperType;

import java.util.Arrays;

public enum ConsulEnum implements EnumMapperType {

    IMPOSSIBLE(0,"상담불가"),

    POSSIBLE(1,"상담가능");

    private final int value;
    private final String name;
    ConsulEnum(int value, String name){
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

    public static ConsulEnum findStatusCode(int value){
        return Arrays.stream(ConsulEnum.values())
                .filter(statusCode -> statusCode.getStatus() == value)
                .findFirst()
                .orElseThrow();
    }
}
