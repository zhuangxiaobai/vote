package com.item.vote.service.serviceImpl;

import com.item.vote.bean.User;
import com.item.vote.mapper.E_UserMapper;
import com.item.vote.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonServiceImpl implements CommonService {


    @Autowired
    private E_UserMapper EUserMapper;


    @Override
    public User login(User user) {
        return EUserMapper.login(user);
    }

//    @Override
//    public Integer selectRoleByUserName(User user) {
//
//        return EUserMapper.selectRoleByUserName(user);
//    }
}
