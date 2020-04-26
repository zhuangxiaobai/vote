package com.item.vote.service;

import com.item.vote.bean.User;

public interface UserService {

    int create(User user); //创建用户

    int userNameExist(User user); //创建用户中的查重
    User getUserById(Integer id); //创建用户中的通过用户id查用户信息

}
