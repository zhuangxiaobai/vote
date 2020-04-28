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

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public int updateVote(Integer id, Vote vote) {


        List<Option> optionList;
        String[] optionsArray;

        Date date = new Date();
        vote.setCreateTime(date);
        vote.setUpdateTime(date);
        //修改vote表
        //int resultVote  = EVoteMapper.updateVote(id,vote);
//        if(resultVote != 1){
//            //throw new RuntimeException();
//            return  0;
//        }



        //删除此投票数据
        int deleteVoteById   = EVoteMapper.deleteVoteById(id);
        if(deleteVoteById !=1){
           return 0;
         }
       //新增投票数据
         int createVoteById = EVoteMapper.createVote(vote);
        if(createVoteById !=1){
            return 0;
        }


       //设置新option对象
        String options = vote.getOptions();
        optionsArray = options.split(",");
        optionList = new ArrayList<>();
        Option option;
        for (int i = 0; i < optionsArray.length; i++) {
            option = new Option();
            option.setP_id(id);
            option.setName(optionsArray[i]);
            option.setCreateTime(date);
            option.setUpdateTime(date);
            optionList.add(option);
        }
        //查一下原来选项有多少
//         int optionsNum = EOptionMapper.selectOptionsByVoteId(id);
//
//        //删除原有的选项
//         int deleteOptionsResult = EOptionMapper.deleteOptionsByVoteId(id);
//         if(deleteOptionsResult != optionsNum){
//             return 0;
//         }

        //添加新选项
        int resultOption = EOptionMapper.createOptions(optionList);

        if (resultOption != optionsArray.length) {
            // throw new RuntimeException();
            return 0;
        }


        return 1;

    }

    @Override
    @Transactional(rollbackFor =  Exception.class)
    public int deleteVote(Integer id) {

        //删除vote，把状态置为2

//          if(deleteVoteResult == 1){
//              //通过投票id删除option
//              int deleteOptionsResult = EOptionMapper.deleteOptionsByVoteId(id);
//              if(deleteOptionsResult !=0){
//
//                   return 1;
//              }
//
//          }



        return EVoteMapper.deleteVoteById(id);
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
