package com.wearwiz.common.kafka;

import com.wearwiz.common.EnumMapperType;

public enum ViewTypeEnum implements EnumMapperType {
    COMMUNITY(1,"IncreaseCommunityViewService");


    private final int value;

    private final String serviceName;

    ViewTypeEnum(int value, String serviceName){
        this.value = value;
        this.serviceName = serviceName;
    }
    @Override
    public int getStatus() {
        return value;
    }

    @Override
    public String getName() {
        return serviceName;
    }
}
