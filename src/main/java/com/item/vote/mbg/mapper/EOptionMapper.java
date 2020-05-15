package com.item.vote.mbg.mapper;

import com.item.vote.mbg.model.EOption;
import com.item.vote.mbg.model.EOptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EOptionMapper {
    long countByExample(EOptionExample example);

    int deleteByExample(EOptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EOption record);

    int insertSelective(EOption record);

    List<EOption> selectByExample(EOptionExample example);

    EOption selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EOption record, @Param("example") EOptionExample example);

    int updateByExample(@Param("record") EOption record, @Param("example") EOptionExample example);

    int updateByPrimaryKeySelective(EOption record);

    int updateByPrimaryKey(EOption record);
}