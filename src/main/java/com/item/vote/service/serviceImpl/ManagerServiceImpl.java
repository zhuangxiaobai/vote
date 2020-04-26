package com.item.vote.service.serviceImpl;

import com.item.vote.bean.User;

import com.item.vote.mapper.E_UserMapper;
import com.item.vote.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private E_UserMapper EUserMapper;



    @Override
    public List<User> getUserList() {
        return EUserMapper.selectUserList();
    }



 /*   @Override
    @Transactional
    public int update(Integer id, User user) {
        return EUserMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    @Transactional
    public int delete(Integer id) {
        return EUserMapper.deleteByPrimaryKey(id);
    }
*/



}
