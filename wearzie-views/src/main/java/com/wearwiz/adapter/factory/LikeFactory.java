package com.wearwiz.adapter.factory;

import com.wearwiz.adapter.service.RegisterLikeService;
import com.wearwiz.common.kafka.LikeTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LikeFactory {

    private final Map<String, RegisterLikeService> likeServiceMap;

    public LikeFactory(Map<String,RegisterLikeService> likeServiceMap){
        this.likeServiceMap = likeServiceMap;
    }

    public RegisterLikeService createRegisterLikeService(LikeTypeEnum type) throws IllegalAccessException {
        RegisterLikeService registerLikeService = likeServiceMap.get(type.getName());

        if(registerLikeService == null){
            throw new IllegalAccessException("Unknown likeService type:" + type.getName());
        }

        return registerLikeService;
    }

}
