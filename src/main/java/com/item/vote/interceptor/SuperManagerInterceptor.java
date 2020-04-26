package com.item.vote.interceptor;

import cn.hutool.json.JSONObject;
import com.item.vote.api.ResultCode;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class SuperManagerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getServletPath();//相对路径
        //   String absoutePath =  request.getRequestURI();//绝对路径
        System.out.println("权限拦截当前请求路径：" + path);
        //  System.out.println("当前请求绝对路径：" + absoutePath);
        System.out.println("=========进入超级管理员拦截器===================");

        //CommonResult commonResult;

        HttpSession session = request.getSession();

        //在拦截器里面判断此用户在不在session中如果在过，不在不让过返回提示让去登录
        String sessionUserRole=(String)session.getAttribute("sessionUserRole");
        if (sessionUserRole != null){
            System.out.println("session中的用户角色:"+sessionUserRole);


            Cookie[] cookies = request.getCookies();

            for(int i=0; i<cookies.length; i++){
                Cookie cookie = cookies[i];
                if("cookieUserRole".equals(cookie.getName())){
                    if("2".equals(cookie.getValue())){
                        System.out.println("=========成功通过超级管理员拦截器===================");
                        return true;
                    }
                }
            }

        }



        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;
        try{
            JSONObject res = new JSONObject();
            res.put("code", ResultCode.FORBIDDEN.getCode());
            res.put("message", ResultCode.FORBIDDEN.getMessage());
            out = response.getWriter();
            out.append(res.toString());

        }
        catch (Exception e){
            e.printStackTrace();
            response.sendError(500,"通过超级管理员拦截器时被拦截返回信息时发生错误,具体查看后台日志");

        }finally{

            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }



}
