package com.werwiz.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {

    @Value("${redis.refresh}")
    private String REFRESH;
    private final RedisTemplate<String,Object> redisTemplate;


    public void saveTokenToRedis(String userId, String tokenValue, Long expiredTime) {
      save(userId,tokenValue,expiredTime);
    }

    public void saveRefreshTokenToRedis(String userId, String tokenValue,Long expiredTime){
        save(REFRESH+userId,tokenValue,expiredTime);
    }

    private void save(String key, String value, Long expiredTime) {
        redisTemplate.opsForValue().set(key, value, expiredTime, TimeUnit.MILLISECONDS);
    }

    public String getValues(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }

    public void deleteValues(String key) {
        redisTemplate.delete(key);
    }
}
