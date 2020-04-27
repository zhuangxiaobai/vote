package com.item.vote.interceptor;

import cn.hutool.json.JSONObject;
import com.item.vote.api.CommonResult;
import com.item.vote.api.ResultCode;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/**
 * 登录拦截器，主要看看是否已经登录
 */
public class LoginInterceptor implements HandlerInterceptor {




    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getServletPath();//相对路径
     //   String absoutePath =  request.getRequestURI();//绝对路径
        System.out.println("当前请求路径：" + path);
      //  System.out.println("当前请求绝对路径：" + absoutePath);
        System.out.println("=========进入登录拦截器===================");

        //CommonResult commonResult;

        HttpSession session = request.getSession();
        if (session != null){
            //在拦截器里面判断此用户在不在session中如果在过，不在不让过返回提示让去登录
            String sessionUserId=(String)session.getAttribute("sessionUserId");
            if (sessionUserId != null){
                System.out.println("session中的用户id:"+sessionUserId);


                Cookie[] cookies = request.getCookies();
                if(cookies != null) {
                    for(int i=0; i<cookies.length; i++){
                        Cookie cookie = cookies[i];
                        if("cookieUserId".equals(cookie.getName())){
                            if(sessionUserId.equals(cookie.getValue())){
                                System.out.println("=========成功通过登录拦截器===================");
                                return true;
                            }

                        }

                    }
                }

            }

        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null ;
        try{
            JSONObject res = new JSONObject();
            res.put("code", ResultCode.UNAUTHORIZEDSESSION.getCode());
            res.put("message", ResultCode.UNAUTHORIZEDSESSION.getMessage());
            //res.put("data", null);
            out = response.getWriter();
            out.append(res.toString());
           // throw new Exception();

        }
        catch (Exception e){
            e.printStackTrace();
            response.sendError(500,"通过登录拦截器时被拦截返回信息时发生错误,具体查看后台日志");

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
