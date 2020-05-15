package com.item.vote.model;


import lombok.Data;

//用户投票的记录 谁投了那个选项，在UserVote中引用
@Data
public class UserVote {

      private  String  name;


      private  String  optionName;


}
