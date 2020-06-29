package com.bianyiit.service;


import com.bianyiit.pojo.User;

public interface UserService {


    User findByUsername(String username);
}