package com.example.demo.service;

import com.example.demo.model.dao.UserMapper;
import com.example.demo.model.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public boolean authenticate(String userId, String password) {
        User user = userMapper.findByUserIdAndPassword(userId, password);
        return user != null;
    }

    public String getUserDisplayName(String username) {
        try {
            return userMapper.getUserDisplayName(username);
        } catch (EmptyResultDataAccessException e) {
            return "Unknown User";
        }
    }

}

