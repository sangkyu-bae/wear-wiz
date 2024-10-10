package com.werwiz.adapter.out.persistence.mapper;

import com.wearwiz.common.EnumMapperType;
import com.werwiz.domain.EnumClassType;
import lombok.Getter;

@Getter
public class EnumClassMapperValue {

    private long id;

    private String name;

    public EnumClassMapperValue(EnumClassType enumClassType){
        this.id = enumClassType.getId();
        this.name = enumClassType.getName();
    }

    public EnumClassMapperValue(EnumMapperType enumMapperType){
        this.id = enumMapperType.getStatus();
        this.name = enumMapperType.getName();
    }
}
