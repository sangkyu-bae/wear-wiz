package com.wearwiz.adapter.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearwiz.adapter.factory.LikeFactory;
import com.wearwiz.adapter.factory.ViewFactory;
import com.wearwiz.adapter.service.IncreaseViewService;
import com.wearwiz.common.kafka.IncreaseViewRequest;
import com.wearwiz.common.kafka.Like;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommunityViewConsumer {

    private final ObjectMapper objectMapper;
    private final ViewFactory viewFactory;

    /**
     * ToDo: 현재 단일 파티션으로 구동 추후 kafka 파티션 증가
     */
    @KafkaListener(topics = "${kafka.view.community}")
    public void CommunityViewListener(String viewObj){
        IncreaseViewRequest request = null;

        try{
            request = objectMapper.readValue(viewObj, IncreaseViewRequest.class);
            IncreaseViewService increaseViewService = viewFactory.createIncreaseViewService(request.getViewTypeEnum());

            increaseViewService.increaseView(request);
        }catch (Exception e){

        }
    }
}
