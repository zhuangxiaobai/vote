package com.item.vote.controller;

import com.item.vote.api.CommonResult;
import com.item.vote.bean.User;
import com.item.vote.service.CommonService;
import com.item.vote.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

//@Api(tags = "UserController", description = "管理员和用户通用的行为 登录登出")
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    // @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response ) {


        CommonResult commonResult;
        int count = commonService.login(user);
        if (count == 1) {

            Integer role = commonService.selectRoleByUserName(user);


            //存放在session
            request.getSession().setAttribute("sessionUserName",user.getName());
            request.getSession().setAttribute("sessionUserRole",Integer.toString(role));
            request.getSession().setMaxInactiveInterval(1800);//单位：秒   默认30分钟。

            //存放cookie
            Cookie cookie1 = new Cookie("cookieUserName", user.getName());
            Cookie cookie2 = new Cookie("cookieUserRole", Integer.toString(role));
            cookie1.setMaxAge(30 * 60);// 设置为30min
            cookie2.setMaxAge(30 * 60);// 设置为30min
            cookie1.setPath("/");
            cookie2.setPath("/");
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


    // @ApiOperation("登出，必须登录才能用")
    @RequestMapping(value = "/quit", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult quit( HttpServletRequest request,HttpServletResponse response) {
        CommonResult commonResult;
        //int count = managerService.quit();

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