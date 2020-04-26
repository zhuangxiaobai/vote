package com.item.vote.controller;

import com.item.vote.api.CommonResult;
import com.item.vote.bean.User;
import com.item.vote.service.ManagerService;
import com.item.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

//@Api(tags = "UserController", description = "用户的注册，修改密码，查看投票，投票")
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;



    //@ApiOperation("用户注册")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody User user) {
        CommonResult commonResult;
        //用户名查重
        int userNameExist = userService.userNameExist(user);

        if (userNameExist == 0 ){
             //创建用户
            int count = userService.create(user);
            if (count == 1) {
                //成功之后通过返回的id去查一遍数据库返回所有数据
                commonResult = CommonResult.success(userService.getUserById(user.getId()));
                //  LOGGER.debug("create success:{}", user);
            } else {
                commonResult = CommonResult.failed("创建用户错误,注册失败");
                //  LOGGER.debug("create failed:{}", user);
            }

        }else{
            //注册用户重名了
            commonResult = CommonResult.validateFailed("用户名已经存在,注册失败");
        }

        return commonResult;
    }









}
