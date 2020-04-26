package com.item.vote.mapper;

import com.item.vote.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface E_UserMapper {




    int insertSelective(User user); //添加一个用户

    int selectByUserName(User user);   //通过用户名获取用户

    Integer selectRoleByUserName(User user); //通过用户名获取角色

    //int deleteByPrimaryKey(Integer id); //通过id删除用户

   // int updateByPrimaryKeySelective(User user); //通过id修改用户信息

    User selectByPrimaryKey(Integer id);//通过id获取用户信息

    List<User> selectUserList(); //获取所有用户列表


    int login(User user);  //登录时的验证
}
