package com.wearwiz.adapter.factory;

import com.wearwiz.adapter.service.IncreaseViewService;
import com.wearwiz.common.kafka.ViewTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class ViewFactory {

    private final Map<String, IncreaseViewService> viewServiceMap;

    public ViewFactory(Map<String,IncreaseViewService> viewServiceMap){
        this.viewServiceMap = viewServiceMap;
    }

    public IncreaseViewService createIncreaseViewService(ViewTypeEnum type) throws IllegalAccessException {
        IncreaseViewService increaseViewService = viewServiceMap.get(type.getName());
        if(increaseViewService == null){
            throw new IllegalAccessException("Unknown createIncreaseViewService type:" + type.getName());
        }

        return increaseViewService;
    }
}
