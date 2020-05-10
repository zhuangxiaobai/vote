
package com.item.vote.config;

import com.item.vote.interceptor.ManagerInterceptor;
import com.item.vote.interceptor.LoginInterceptor;
import com.item.vote.interceptor.SuperManagerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.constraints.Null;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {



/**
     * 拦截器配置
     * @param registry
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器
        InterceptorRegistration interceptorRegistry=registry.addInterceptor(new LoginInterceptor());
        //用户注册不拦截，随时注册
        //用户登录不拦截，去登录接口里面执行登录操作成功就存入session
        interceptorRegistry.addPathPatterns("/**") //需要拦截的路径
        .excludePathPatterns("/common/login")     //不需要拦截的路径
        .excludePathPatterns("/user/create")
                .excludePathPatterns("/error")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");//不拦截swagger相关资源


       //管理员拦截器，在登录拦截器之后，主要拦截一些管理员和超级管理员的操作
        InterceptorRegistration interceptorRegistry2=registry.addInterceptor(new ManagerInterceptor());
        interceptorRegistry2.addPathPatterns("/manager/*")
                .excludePathPatterns("/error");




        //超级管理员拦截器，在最后，主要拦截超级管理员的操作
        InterceptorRegistration interceptorRegistry3=registry.addInterceptor(new SuperManagerInterceptor());
        interceptorRegistry3.addPathPatterns("/manager/deleteVote/*")
                .excludePathPatterns("/error");









    }




}

