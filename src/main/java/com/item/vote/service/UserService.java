package com.item.vote.service;

import com.item.vote.bean.User;
import com.item.vote.model.VoteUserSelect;
import com.item.vote.model.VoteVo;

import java.util.List;

public interface UserService {

    int create(User user); //创建用户

    int userNameExist(User user); //创建用户中的查重

    int updatePwd(User user);   //用户修改密码

    List<VoteUserSelect> getUserVoteList(Integer id);//用户历史投票列表查寻

    int doVote(Integer uid, Integer vid, Integer oid); //用户投票动作

    //  User getUserById(Integer id); //创建用户中的通过用户id查用户信息

}
