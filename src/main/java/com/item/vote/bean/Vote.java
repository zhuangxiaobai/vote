package com.item.vote.bean;

import com.item.vote.util.DateUtil;
import lombok.Data;

import java.util.Date;

@Data
public class Vote {

    private Integer id;

    private String name;

    private Integer type;

    private Integer status;

    //传过来的用逗号分隔的字符串
    private String options;

    private Date createTime;

    private Date updateTime;

    public String getCreateTime() {
        return DateUtil.dateToString(createTime);
    }

    public String getUpdateTime() {
        return DateUtil.dateToString(updateTime);
    }


}
