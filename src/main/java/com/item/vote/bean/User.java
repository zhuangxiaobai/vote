package com.item.vote.bean;

import com.item.vote.util.DateUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用户管理员通用
 */
@Data
public class User {

    private Integer id;

    @Email
    @ApiModelProperty(value = "此字段为email格式")
    private String name;

    private String password;


    private Integer role;

    private Date createTime;

    private Date updateTime;

    public String getCreateTime() {
        return DateUtil.dateToString(createTime);
    }

    public String getUpdateTime() {
        return DateUtil.dateToString(updateTime);
    }


}
