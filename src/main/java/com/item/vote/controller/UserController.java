package com.item.vote.controller;

import com.item.vote.api.CommonResult;
import com.item.vote.bean.Option;
import com.item.vote.bean.User;
import com.item.vote.model.VoteUserSelect;
import com.item.vote.model.VoteVo;
import com.item.vote.service.ManagerService;
import com.item.vote.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "UserController", description = "用户的行为")
@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;



    @ApiOperation("用户注册")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody @Valid User user) {
        CommonResult commonResult;
        //用户名查重
        int userNameExist = userService.userNameExist(user);

        if (userNameExist == 0 ){
             //创建用户
            int count = userService.create(user);
            if (count == 1) {
                //成功之后通过返回的id去查一遍数据库返回所有数据
                //commonResult = CommonResult.success(userService.getUserById(user.getId()));
                //  LOGGER.debug("create success:{}", user);
                commonResult = CommonResult.success("创建用户成功");
            } else {
                commonResult = CommonResult.failed("创建用户错误,注册失败");
                //  LOGGER.debug("create failed:{}", user);
            }

        }else{
            //注册用户重名了
           commonResult = CommonResult.userExist();
        }

        return commonResult;
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePwd", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatePwd(@RequestBody User user) {
        CommonResult commonResult;

        int count = userService.updatePwd(user);
        if (count == 1) {
            commonResult = CommonResult.success("密码修改成功");
        } else {
            commonResult = CommonResult.failed("密码修改失败");

        }

        return commonResult;
    }


    @ApiOperation("获取用户投票历史记录")
    @RequestMapping(value = "/userVoteList/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<VoteUserSelect>> getUserVoteList(@PathVariable("id") Integer id) {
        return CommonResult.success(userService.getUserVoteList(id));
    }



    @ApiOperation("获取创建时间为7天内的投票列表")
    @RequestMapping(value = "/voteListLimit", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<VoteVo>> getVoteListLimit() {
        return CommonResult.success(userService.getVoteListLimit());
    }







    @ApiOperation("用户投票")
    @RequestMapping(value = "/doVote/{uid}/{vid}/{oid}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult doVote(@PathVariable("uid")Integer uid,@PathVariable("vid")Integer vid,@PathVariable("oid")Integer oid) {
        CommonResult commonResult;

            int haveVoted = userService.haveVoted(uid,vid);
           if(haveVoted !=1){

            //用户投票
            int count = userService.doVote(uid,vid,oid);
            if (count == 1) {
                //去获取各个option的number
               List<Option> optionList = userService.getOptions(vid);


                commonResult = CommonResult.success(optionList);
            } else {
                commonResult = CommonResult.failed("投票失败");

            }

           }else{
               commonResult = CommonResult.failed("您已经进行过投票，请勿刷票");
           }

        return commonResult;
    }





}
