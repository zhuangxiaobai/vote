package com.item.vote.bean;

import com.item.vote.util.DateUtil;
import lombok.Data;

import java.util.Date;


@Data
public class Log {


    private Integer id;

    private Integer vid;

    private Integer uid;

    private Integer oid;

    private Date createTime;

    private Date updateTime;

    public String getCreateTime() {
        return DateUtil.dateToString(createTime);
    }

    public String getUpdateTime() {
        return DateUtil.dateToString(updateTime);
    }


}
