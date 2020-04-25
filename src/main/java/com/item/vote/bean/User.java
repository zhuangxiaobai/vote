package com.item.vote.bean;

import lombok.Data;

/**
 * 用户管理员通用
 */
@Data
public class User {

    private Long id;

    private String name;

    private String password;


    private String role;


}
