package com.wearwiz.adaptor.out.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wearwiz.common.kafka.IncreaseViewRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Slf4j
@Component
public class ViewProducer {

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Value("${kafka.view.community}")
    private String viewCommunity;

    public void sendViewIncrease(IncreaseViewRequest request){

        try{
            String jsonIncreaseViewRequest = objectMapper.writeValueAsString(request);
            kafkaTemplate.send(viewCommunity,jsonIncreaseViewRequest);
        }catch (Exception e){
            log.error("sendViewIncrease" ,e);
        }
    }
}
