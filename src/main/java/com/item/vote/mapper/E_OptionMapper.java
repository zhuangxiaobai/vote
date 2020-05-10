package com.item.vote.mapper;

import com.item.vote.bean.Option;
import com.item.vote.bean.Vote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface E_OptionMapper {
    int createOptions(List<Option> optionList );



    List<Option> selectOptionListByVoteId(Integer voteId);

    Option selectOptionById(Integer optionId);

    int updateNumber(@Param("vid")Integer vid,@Param("oid") Integer oid);

    Option selectOptionByOidAndVid(@Param("oid") Integer oid,@Param("voteId")Integer voteId);


    //  int deleteOptionsByVoteId(Integer id);

    // int selectOptionsByVoteId(Integer id);


}
