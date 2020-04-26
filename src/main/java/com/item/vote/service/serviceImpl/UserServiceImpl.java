package com.item.vote.service.serviceImpl;

import com.item.vote.bean.User;
import com.item.vote.mapper.E_UserMapper;
import com.item.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private E_UserMapper EUserMapper;


    @Override
    @Transactional
    public int create(User user) {


        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);

        return EUserMapper.insertSelective(user);
    }

    @Override
    public int userNameExist(User user) {

        return EUserMapper.selectByUserName(user);
    }

    @Override
    public User getUserById(Integer id) {
        return  EUserMapper.selectByPrimaryKey(id);
    }
}
