package com.item.vote.mapper;

import com.item.vote.bean.Option;
import com.item.vote.bean.Vote;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface E_OptionMapper {
    int createOptions(List<Option> optionList );
}
