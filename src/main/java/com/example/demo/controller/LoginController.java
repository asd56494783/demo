package com.example.demo.controller;

import com.example.demo.logger.LoginEvent;
import com.example.demo.model.request.LoginRequest;
import com.example.demo.service.RedisService;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class LoginController {
    private final UserService userService;
    private final RedisService redisService;
    private final ApplicationContext applicationContext;

    private final TokenService tokenService;

    public LoginController(UserService userService, RedisService redisService, ApplicationContext applicationContext, TokenService tokenService) {
        this.userService = userService;
        this.redisService = redisService;
        this.applicationContext = applicationContext;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        if (userService.authenticate(request.getUsername(), request.getPassword())) {
            String accessToken = tokenService.generateAccessToken(request.getUsername());
            String oldAccessToken = tokenService.getAccessToken(request.getUsername());

            if (oldAccessToken != null) {
                tokenService.invalidateAccessToken(request.getUsername());
            }

            redisService.storeAccessToken(request.getUsername(), accessToken);

            applicationContext.publishEvent(new LoginEvent(this, request.getUsername(), true));

            return ResponseEntity.ok(accessToken);
        } else {
            applicationContext.publishEvent(new LoginEvent(this, request.getUsername(), false));
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("登入失敗");
        }
    }


    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(@RequestParam String accessToken) {
        String username = redisService.getUsernameByAccessToken(accessToken);
        if (username != null) {
            String displayName = userService.getUserDisplayName(username);
            return ResponseEntity.ok(displayName);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token無效");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestParam String accessToken) {
        boolean removed = redisService.removeAccessToken(accessToken);

        if (removed) {
            return ResponseEntity.ok("登出成功");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access Token無效");
        }
    }

}

