package com.item.vote.mapper;

import com.item.vote.bean.Vote;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface E_VoteMapper {
    int createVote(Vote vote);
}
