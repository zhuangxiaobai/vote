package com.item.vote.model;

import com.item.vote.bean.Option;
import com.item.vote.bean.Vote;
import lombok.Data;

import java.util.List;
/*
 投票包含选项的列表返回前台
 */
@Data
public class VoteVo {

    Vote vote;

    List<Option> optionList;


}
