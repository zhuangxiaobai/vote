package com.item.vote.mapper;

import com.item.vote.bean.Vote;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Mapper
public interface E_VoteMapper {
    int createVote(Vote vote);


    int deleteVoteById(Integer id);

    List<Vote> selectVoteList();

    Vote selectVoteById(Integer voteId);

    List<Vote> selectVoteListLimit();


    // int updateVote(@Param("id") Integer id,@Param("vote") Vote vote);

}
