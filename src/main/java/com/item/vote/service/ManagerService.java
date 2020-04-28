package com.item.vote.service;

import com.item.vote.bean.User;
import com.item.vote.bean.Vote;

import java.util.List;

public interface ManagerService {


//    int update(Integer id, User user);
//
//    int delete(Integer id);



    List<User> getUserList(); //获取用户列表不分页


    int createVote(Vote vote);//创建投票

    int updateVote(Integer id, Vote vote); //修改投票


    int deleteVote(Integer id);  //删除投票
}
