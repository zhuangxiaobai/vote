package com.item.vote.service.serviceImpl;

import com.item.vote.bean.Option;
import com.item.vote.bean.User;
import com.item.vote.bean.Vote;
import com.item.vote.mapper.E_OptionMapper;
import com.item.vote.mapper.E_UserMapper;
import com.item.vote.mapper.E_VoteMapper;
import com.item.vote.model.VoteVo;
import com.item.vote.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {


    @Autowired
    private E_UserMapper EUserMapper;

    @Autowired
    private E_VoteMapper EVoteMapper;

    @Autowired
    private E_OptionMapper EOptionMapper;



    @Override
    public User login(User user) {
        return EUserMapper.login(user);
    }

    @Override
    public List<VoteVo> getVoteVoList() {

      List<Vote>  voteList  = EVoteMapper.selectVoteList();
      //返回前台的对象集合
      List<VoteVo>  voteVoList = new ArrayList<>();
      VoteVo voteVo;
      for (Vote vote:voteList){
          voteVo = new VoteVo();
          voteVo.setVote(vote);

          List<Option>  optionList  = EOptionMapper.selectOptionListByVoteId(vote.getId());

          voteVo.setOptionList(optionList);
          voteVoList.add(voteVo);

      }



        return voteVoList;
    }







//    @Override
//    public Integer selectRoleByUserName(User user) {
//
//        return EUserMapper.selectRoleByUserName(user);
//    }
}
