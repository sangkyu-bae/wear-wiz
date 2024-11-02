package com.wearwiz.adapter.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisSortedService {

    private final StringRedisTemplate redisTemplate;

    public int findByKey(String keyName, long keyValue) {
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

    public void updateScore(String keyName, long keyId, int value) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        String key =String.valueOf(keyId);
        zSetOps.incrementScore(keyName, key, value);
    }

    public void register(String keyName, long keyId, int value) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        String key =String.valueOf(keyId);
        zSetOps.add(keyName, key, value);
    }

    public List<String> findByPaging(int limit,String key) {
        ZSetOperations zSetOps = redisTemplate.opsForZSet();
        Set<String> rangeRakingSet = zSetOps.reverseRange(key,0,limit-1);
        return new ArrayList<>(rangeRakingSet);
    }


}
