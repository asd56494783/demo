package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public String generateAccessToken(String username) {
        String accessToken = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(username, accessToken);

        return accessToken;
    }

    public String getAccessToken(String username) {
        return redisTemplate.opsForValue().get(username);
    }

    public boolean validateAccessToken(String username, String accessToken) {
        String storedToken = redisTemplate.opsForValue().get(username);

        return accessToken.equals(storedToken);
    }

    public void invalidateAccessToken(String username) {
        redisTemplate.delete(username);
    }
}
