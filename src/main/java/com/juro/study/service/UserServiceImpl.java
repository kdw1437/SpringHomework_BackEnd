package com.juro.study.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.juro.study.mapper.UserMapper;
import com.juro.study.model.User;
import com.juro.study.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User createUser(String author, String password) {
        User user = new User();
        user.setAuthor(author);
        user.setPassword(password); 
        userMapper.insert(user);
        return user;
    }

    @Override
    public User getUserByAuthor(String author) {
        return userMapper.findByAuthor(author);
    }

    @Override
    public boolean changePassword(String author, String oldPassword, String newPassword) {
        return userMapper.changePassword(author, oldPassword, newPassword) > 0;
    }

    @Override
    public boolean deleteUser(String author, String password) {
        return userMapper.deleteByAuthor(author, password) > 0;
    }
}