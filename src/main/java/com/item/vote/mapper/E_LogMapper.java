package com.item.vote.mapper;

import com.item.vote.bean.Log;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface E_LogMapper {


      List<Log> selectLogsByUId(Integer uid);


    int create(@Param("uid") Integer uid, @Param("vid") Integer vid, @Param("oid") Integer oid);


    int selectHaveVoted(@Param("uid") Integer uid, @Param("vid") Integer vid);
}
