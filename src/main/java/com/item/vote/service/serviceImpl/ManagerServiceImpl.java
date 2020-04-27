package com.item.vote.service.serviceImpl;

import com.item.vote.bean.Option;
import com.item.vote.bean.User;

import com.item.vote.bean.Vote;
import com.item.vote.mapper.E_OptionMapper;
import com.item.vote.mapper.E_UserMapper;
import com.item.vote.mapper.E_VoteMapper;
import com.item.vote.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private E_UserMapper EUserMapper;

    @Autowired
    private E_VoteMapper EVoteMapper;

    @Autowired
    private E_OptionMapper EOptionMapper;


    @Override
    public List<User> getUserList() {
        return EUserMapper.selectUserList();
    }

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public int createVote(Vote vote) {
      List<Option> optionList;
      String[] optionsArray;

      Date date = new Date();
      vote.setCreateTime(date);
      vote.setUpdateTime(date);
      //插入vote表
       int resultVote  = EVoteMapper.createVote(vote);
      if(resultVote != 1){
          //throw new RuntimeException();
          return  0;
      }

          Integer voteId = vote.getId();
          String options = vote.getOptions();
          optionsArray = options.split(",");
          optionList = new ArrayList<>();
          Option option;
          for (int i = 0; i < optionsArray.length; i++) {
              option = new Option();
              option.setP_id(voteId);
              option.setName(optionsArray[i]);
              option.setCreateTime(date);
              option.setUpdateTime(date);
              optionList.add(option);
          }

          int resultOption = EOptionMapper.createOptions(optionList);

          if (resultOption != optionsArray.length) {
             // throw new RuntimeException();
              return 0;
          }


          return 1;


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
