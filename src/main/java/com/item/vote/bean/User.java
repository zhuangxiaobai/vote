package com.item.vote.bean;

import lombok.Data;

import java.util.Date;

/**
 * 用户管理员通用
 */
@Data
public class User {

    private Integer id;

    private String name;

    private String password;


    private Integer role;

    private Date createTime;

    private Date UpdateTime;


}
