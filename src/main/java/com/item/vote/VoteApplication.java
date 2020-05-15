package com.item.vote;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@MapperScan({"com.item.vote.mapper","com.item.vote.mbg.mapper"})
public class VoteApplication extends SpringBootServletInitializer {


    /**
     * 从外部启动tomcat要
     * 继承SpringBootServletInitializer类，重写configure方法
     * */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(VoteApplication.class);
    }



    public static void main(String[] args) {
        SpringApplication.run(VoteApplication.class, args);
    }

}
