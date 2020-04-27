package com.item.vote.controller;


import com.item.vote.api.CommonResult;
import com.item.vote.bean.User;
import com.item.vote.bean.Vote;
import com.item.vote.service.ManagerService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

//@Api(tags = "UserController", description = "管理员的行为")
@Controller
@RequestMapping("/manager")
public class ManagerController {
    @Autowired
    private ManagerService managerService;

  //  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    // @ApiOperation("获取用户列表不分页")
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<User>> getUserList() {
        return CommonResult.success(managerService.getUserList());
    }



    //@ApiOperation("新增投票")
    @RequestMapping(value = "/createVote", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult createVote(@RequestBody Vote vote) {
        CommonResult commonResult;

            int count = managerService.createVote(vote);
            if (count == 1) {
                commonResult = CommonResult.success("创建投票成功");
            } else {
                commonResult = CommonResult.failed("创建投票失败");

            }

        return commonResult;
    }

    //@ApiOperation("修改投票")
    @RequestMapping(value = "/updateVote/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateVote(@PathVariable("id") Integer id, @RequestBody Vote vote) {
        CommonResult commonResult;

        int count = managerService.updateVote(id,vote);
        if (count == 1) {
            commonResult = CommonResult.success("修改投票成功");
        } else {
            commonResult = CommonResult.failed("修改投票失败");

        }

        return commonResult;
    }

    //@ApiOperation("删除投票")
    @RequestMapping(value = "/deleteVote/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult deleteVote(@PathVariable("id") Integer id) {
        CommonResult commonResult;

        int count = managerService.deleteVote(id);
        if (count == 1) {
            commonResult = CommonResult.success("删除投票成功");
        } else {
            commonResult = CommonResult.failed("删除投票失败");

        }

        return commonResult;
    }












/*

    // @ApiOperation("更新指定id的用户信息，管理员")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") @ApiParam("用户ID") Integer id, @RequestBody User user) {
        CommonResult commonResult;
        int count = managerService.update(id, user);
        if (count == 1) {
            commonResult = CommonResult.success(user);
          //  LOGGER.debug("update success:{}", user);
        } else {
            commonResult = CommonResult.failed("操作失败");
           // LOGGER.debug("update failed:{}", user);
        }
        return commonResult;
    }

    //@ApiOperation("删除指定id的用户")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable("id") Integer id) {
        int count = managerService.delete(id);
        if (count == 1) {
           // LOGGER.debug("delete success :id={}", id);
            return CommonResult.success(null);
        } else {
          //  LOGGER.debug("delete failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }
*/








}
