package com.wearwiz.adapter.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearwiz.common.kafka.CommunityLike;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CommunityLikeConsumer {

    private final ObjectMapper objectMapper;

    /**
     * ToDo: 현재 단일 파티션으로 구동 추후 kafka 파티션 증가
     */
    @KafkaListener(topics = "${kafka.like.community}")
    public void likeByCommunityListener(String likeObj){
        CommunityLike like = null;

        try{
            like = objectMapper.readValue(likeObj, CommunityLike.class);

        }catch (Exception e){

        }
    }
}
