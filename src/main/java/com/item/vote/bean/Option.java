package com.item.vote.bean;

import com.item.vote.util.DateUtil;
import lombok.Data;

import java.util.Date;

@Data
public class Option {


    private Integer id;

    private String name;

    private Integer p_id;

    private Integer number;

    private Date createTime;

    private Date updateTime;

    public String getCreateTime() {
        return DateUtil.dateToString(createTime);
    }

    public String getUpdateTime() {
        return DateUtil.dateToString(updateTime);
    }




}
