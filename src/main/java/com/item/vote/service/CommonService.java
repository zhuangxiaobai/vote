package com.item.vote.service;

import com.item.vote.bean.User;
import com.item.vote.model.VoteVo;

import java.util.List;

public interface CommonService {


    User login(User user);//登录

    List<VoteVo> getVoteVoList();//获取投票集合包含选项

    //  Integer selectRoleByUserName(User user); //登录下的通过用户名获取用户角色
}
