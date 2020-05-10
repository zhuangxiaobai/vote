package com.item.vote.controller;

import com.item.vote.api.CommonResult;
import com.item.vote.bean.User;
import com.item.vote.model.VoteVo;
import com.item.vote.service.CommonService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

@Api(tags = "CommonController", description = "管理员和用户通用的行为")
@Controller
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response ) {


        CommonResult commonResult;
        User userBack = commonService.login(user);
        if (userBack != null) {

           // Integer role = commonService.selectRoleByUserName(user);


           //存放在session
            request.getSession().setAttribute("sessionUserId",Integer.toString(userBack.getId()));
            request.getSession().setAttribute("sessionUserRole",Integer.toString(userBack.getRole()));
            request.getSession().setMaxInactiveInterval(1800);//单位：秒   默认30分钟。
            //存放cookie 不用放前台了
            Cookie cookie1 = new Cookie("cookieUserId", Integer.toString(userBack.getId()));
            Cookie cookie2 = new Cookie("cookieUserRole", Integer.toString(userBack.getRole()));
            cookie1.setMaxAge(30 * 60);// 设置为30min
            cookie2.setMaxAge(30 * 60);// 设置为30min
            cookie1.setPath("/");
            cookie2.setPath("/");
            response.addCookie(cookie1);
            response.addCookie(cookie2);

            commonResult = CommonResult.success(userBack);
            //  LOGGER.debug("create success:{}", user);
        } else {
            commonResult = CommonResult.failed("登录失败");
            //  LOGGER.debug("create failed:{}", user);
        }
        return commonResult;

    }




    @ApiOperation("获取投票列表不分页")
    @RequestMapping(value = "/voteList", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<VoteVo>> getVoteList() {
        return CommonResult.success(commonService.getVoteVoList());
    }







    @ApiOperation("上传图片")
    @RequestMapping(value = "/uploadImage",produces="application/json;charset=UTF-8",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult uploadImage(@RequestParam(value = "myfiles" ) MultipartFile[] files,
                                    HttpServletRequest request) {

        List<String> list = new ArrayList<>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile file = files[i];
                // 保存文件
                list = saveFile(request, file, list);
            }
        }
        //测试
        for (int i = 0; i < list.size(); i++) {
            System.out.println("集合里面的数据" + list.get(i));
        }
        // 数组转String字符串
        String newStr = StringUtils.join(list, ",");
        System.out.println(newStr);


        return CommonResult.success(newStr);
    }

    private List<String> saveFile(HttpServletRequest request,
                                  MultipartFile file, List<String> list) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 保存的文件路径(如果用的是Tomcat服务器，文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
                // )
               // String filePath = "C:/fileUpload/picture" + (new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + file.getOriginalFilename());

               String fileName = "picture"+(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + file.getOriginalFilename());
                String filePath=  request.getServletContext().getRealPath("/upload/"+fileName);

                // 获取当前项目的完整url
                String requestURL = request.getRequestURL().toString();
                // 获取当前项目的请求路径uri
                String requestURI = request.getRequestURI();
                // 得到去掉了uri的路径
                String url = requestURL.substring(0, requestURL.length()-requestURI.length() + 1);

               String urlBack = url+"vote/upload/"+fileName;


               list.add(urlBack);
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists()) {
                    saveDir.getParentFile().mkdirs();
                }
                // 转存文件
                file.transferTo(saveDir);


                return list;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }



    @ApiOperation("登出，必须登录才能用")
    @RequestMapping(value = "/quit", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult quit( HttpServletRequest request,HttpServletResponse response) {
        CommonResult commonResult;
        //int count = managerService.quit();

        Enumeration em = request.getSession().getAttributeNames();  //得到session中所有的属性名
        while (em.hasMoreElements()) {
            request.getSession().removeAttribute(em.nextElement().toString()); //遍历删除session中的值
        }
          //不用cookie放前台了
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
