package com.item.vote.service;

import com.item.vote.bean.User;

import java.util.List;

public interface UserService {
    int create(User user);

    int update(Long id, User user);

    int delete(Long id);

    User getUserById(Long id);

    List<User> getUserList();

    int login(User user);

    int userNameExist(User user);

    String selectRoleByUserName(User user);

}
