package com.juro.study.service;

import com.juro.study.model.User;

public interface UserService {
    User createUser(String author, String password);
    User getUserByAuthor(String author);
    boolean changePassword(String author, String oldPassword, String newPassword);
    boolean deleteUser(String author, String password);  // Updated
}