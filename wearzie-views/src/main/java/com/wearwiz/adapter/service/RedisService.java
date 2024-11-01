package com.wearwiz.adapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class RedisService {

    private final StringRedisTemplate redisTemplate;

    public void incrementValue(String keyName, long keyId, int value) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        String key =String.valueOf(keyId);
        zSetOps.add(keyName, key, value);
    }

    public int findCountByKey(String keyName, long keyValue) {
        log.info("keyName : {}",keyName);
        log.info("productId : {}",keyValue);

        String key =String.valueOf(keyValue);
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Double score = zSetOps.score(keyName, key);
        log.info("score : {}",score);
        if (score != null) {
            return (int) Math.round(score);
        } else {
            return 0;
        }
    }
}
