package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void storeAccessToken(String username, String accessToken) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(username))) {
            redisTemplate.delete(username);
        }
        redisTemplate.opsForValue().set(username, accessToken);
    }

    public String getUsernameByAccessToken(String accessToken) {
        return redisTemplate.opsForValue().get(accessToken);
    }

    public boolean removeAccessToken(String accessToken) {
        try {
            redisTemplate.delete(accessToken);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
