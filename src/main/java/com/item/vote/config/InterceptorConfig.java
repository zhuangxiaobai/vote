package com.item.vote.config;

import com.item.vote.interceptor.JurisdictionInterceptor;
import com.item.vote.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
        .excludePathPatterns("/user/create")     //不需要拦截的路径
        .excludePathPatterns("/user/login");


       //权限拦截器，在登录拦截器之后，主要拦截一些管理员的专属操作
        InterceptorRegistration interceptorRegistry2=registry.addInterceptor(new JurisdictionInterceptor());
        interceptorRegistry2.addPathPatterns("/user/**")
        .excludePathPatterns("/user/create").excludePathPatterns("/user/login").excludePathPatterns("/user/quit");









    }




}
