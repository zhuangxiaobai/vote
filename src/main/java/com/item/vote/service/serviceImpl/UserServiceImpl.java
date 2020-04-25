package com.item.vote.service.serviceImpl;

import com.item.vote.bean.User;

import com.item.vote.mapper.UserMapper;
import com.item.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private  UserMapper userMapper;

    @Override
    @Transactional
    public int create(User user) {

        return userMapper.insertSelective(user);
    }

    @Override
    public int userNameExist(User user) {

        return userMapper.selectByUserName(user);
    }

    @Override
    public String selectRoleByUserName(User user) {

        return userMapper.selectRoleByUserName(user);
    }


    @Override
    @Transactional
    public int update(Long id, User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional
    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public User getUserById(Long id) {
        return  userMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectUserList();
    }

    @Override
    public int login(User user) {
        return userMapper.login(user);
    }
}
