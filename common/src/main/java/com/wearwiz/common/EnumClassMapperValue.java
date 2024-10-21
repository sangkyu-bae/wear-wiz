package com.wearwiz.common;
import lombok.Getter;

@Getter
public class EnumClassMapperValue {

    private int id;

    private String name;

    public EnumClassMapperValue(EnumMapperType enumMapperType){
        this.id = enumMapperType.getStatus();
        this.name = enumMapperType.getName();
    }
}
