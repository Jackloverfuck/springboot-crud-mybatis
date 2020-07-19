package com.zhou.service;

import com.zhou.pojo.User;

public interface UserService {

    User selectByName(String username);

    User selectPasswordByName(String userName,String password);

}
