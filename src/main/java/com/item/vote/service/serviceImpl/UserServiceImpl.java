package com.item.vote.service.serviceImpl;

import com.item.vote.bean.Log;
import com.item.vote.bean.Option;
import com.item.vote.bean.User;
import com.item.vote.bean.Vote;
import com.item.vote.mapper.E_LogMapper;
import com.item.vote.mapper.E_OptionMapper;
import com.item.vote.mapper.E_UserMapper;
import com.item.vote.mapper.E_VoteMapper;
import com.item.vote.model.VoteUserSelect;
import com.item.vote.model.VoteVo;
import com.item.vote.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private E_UserMapper EUserMapper;


    @Autowired
    private E_VoteMapper EVoteMapper;

    @Autowired
    private E_OptionMapper EOptionMapper;

    @Autowired
    private E_LogMapper ELogMapper;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(User user) {


        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);

        return EUserMapper.insertSelective(user);
    }

    @Override
    public int userNameExist(User user) {

        return EUserMapper.selectByUserName(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updatePwd(User user) {
        Date date = new Date();

        user.setUpdateTime(date);

        return EUserMapper.updatePwd(user);
    }

    @Override
    public List<VoteUserSelect> getUserVoteList(Integer uid) {
        //返回前台的对象
        List<VoteUserSelect> voteUserSelectList = new ArrayList<>();

        List<Log> logList =  ELogMapper.selectLogsByUId(uid);
        VoteUserSelect voteUserSelect;
        for(Log log:logList){
            voteUserSelect = new VoteUserSelect();
            Integer voteId = log.getVid();
            Vote vote = EVoteMapper.selectVoteById(voteId);

            Integer optionId = log.getOid();
            Option option = EOptionMapper.selectOptionById(optionId);

            voteUserSelect.setVoteName(vote.getName());
            voteUserSelect.setSelectOption(option.getName());


            voteUserSelectList.add(voteUserSelect);
        }


        return voteUserSelectList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int doVote(Integer uid, Integer vid, Integer oid) {


        return ELogMapper.create(uid,vid,oid);
    }

//    @Override
//    public User getUserById(Integer id) {
//        return  EUserMapper.selectByPrimaryKey(id);
//    }
}
