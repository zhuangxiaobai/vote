package com.item.vote.model;

import com.item.vote.bean.Log;
import com.item.vote.bean.Option;
import com.item.vote.bean.Vote;
import lombok.Data;
import sun.net.dns.ResolverConfiguration;

import java.util.List;


//管理员通过voteId查询返回投票详情返回前端
//投票内容，需要用户投票记录和每个选项选择人数
@Data
public class VoteId {


    private Vote vote;
    private List<UserVote> userVotes;

    private List<Option>  options;




}
