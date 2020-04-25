package com.item.vote.controller;


import com.item.vote.api.CommonResult;
import com.item.vote.bean.User;
import com.item.vote.service.UserService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.List;

//@Api(tags = "UserController", description = "用户的注册，登录，删除，修改，查询")
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

  //  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    //@ApiOperation("用户注册，通用")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody  User user) {
        CommonResult commonResult;
        //用户名查重
        int userNameExist = userService.userNameExist(user);

        if (userNameExist == 0 ){

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

   // @ApiOperation("更新指定id的用户信息，管理员")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable("id") @ApiParam("用户ID") Long id, @RequestBody User user) {
        CommonResult commonResult;
        int count = userService.update(id, user);
        if (count == 1) {
            commonResult = CommonResult.success(user);
          //  LOGGER.debug("update success:{}", user);
        } else {
            commonResult = CommonResult.failed("操作失败");
           // LOGGER.debug("update failed:{}", user);
        }
        return commonResult;
    }

    //@ApiOperation("删除指定id的用户，管理员")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable("id") Long id) {
        int count = userService.delete(id);
        if (count == 1) {
           // LOGGER.debug("delete success :id={}", id);
            return CommonResult.success(null);
        } else {
          //  LOGGER.debug("delete failed :id={}", id);
            return CommonResult.failed("操作失败");
        }
    }

   // @ApiOperation("获取指定id的用户详情，管理员")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<User> getUserById(@PathVariable("id") Long id) {
        return CommonResult.success(userService.getUserById(id));
    }


    // @ApiOperation("获取所有用户详情不分页"，管理员)
    @RequestMapping(value = "/userList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<User>> getUserList() {
        return CommonResult.success(userService.getUserList());
    }


    // @ApiOperation("用户登录，通用")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody  User user, HttpServletRequest request,HttpServletResponse response ) {
            CommonResult commonResult;
            int count = userService.login(user);
            if (count == 1) {

                String role = userService.selectRoleByUserName(user);


                //存放在session
                request.getSession().setAttribute("sessionUserName",user.getName());
                request.getSession().setAttribute("sessionUserRole",role);
                request.getSession().setMaxInactiveInterval(1800);;//单位：秒   默认30分钟。

                //存放cookie
                Cookie cookie1 = new Cookie("cookieUserName", user.getName());
                Cookie cookie2 = new Cookie("cookieUserRole", role);
                cookie1.setMaxAge(30 * 60);// 设置为30min
                cookie1.setMaxAge(30 * 60);// 设置为30min
               // cookie.setPath("/");
                response.addCookie(cookie1);
                response.addCookie(cookie2);


                commonResult = CommonResult.success("登陆成功");
                //  LOGGER.debug("create success:{}", user);
            } else {
                commonResult = CommonResult.failed("登录失败");
                //  LOGGER.debug("create failed:{}", user);
            }
            return commonResult;

    }



    // @ApiOperation("用户登出，必须登录才能用，通用")
    @RequestMapping(value = "/quit", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult quit( HttpServletRequest request,HttpServletResponse response) {
        CommonResult commonResult;
        //int count = userService.quit();

        Enumeration em = request.getSession().getAttributeNames();  //得到session中所有的属性名
        while (em.hasMoreElements()) {
           request.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
        }

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(int i=0; i<cookies.length; i++){
                Cookie cookie = cookies[i];
                if("cookieUserName".equals(cookie.getName()) || "cookieUserRole".equals(cookie.getName()) ){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    }

                }

            }

        commonResult =  CommonResult.success("session和cookie已清空");


        return commonResult;

    }




}
