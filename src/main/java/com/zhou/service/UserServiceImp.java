package com.zhou.service;

import com.zhou.mapper.UserMapper;
import com.zhou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    @Override
    public User selectPasswordByName(String userName,String password) {
        return userMapper.selectPasswordByName(userName,password);
    }
}
