package com.example.demo.model.dao;

import com.example.demo.model.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT * FROM users WHERE user_id = #{userId} AND password = #{password}")
    User findByUserIdAndPassword(@Param("userId") String userId, @Param("password") String password);

    @Select("SELECT display_name FROM users WHERE username = #{username}")
    String getUserDisplayName(@Param("username") String username);
}
