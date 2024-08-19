package com.juro.study.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.juro.study.model.User;

@Mapper
public interface UserMapper {
    int insert(User user);
    User findByAuthor(String author);
    int changePassword(@Param("author") String author, 
            @Param("oldPassword") String oldPassword, 
            @Param("newPassword") String newPassword);
    int deleteByAuthor(String author, String password);  // Updated
}