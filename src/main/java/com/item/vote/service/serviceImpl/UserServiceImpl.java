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
import com.item.vote.util.Md5;
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

        if (Md5.getMD5String(user.getPassword()) == null){
            return 0;
        }
        user.setPassword(Md5.getMD5String(user.getPassword()));




      //  System.out.println("md5--"+Md5.getMD5String(user.getPassword()));
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
        if (Md5.getMD5String(user.getPassword()) == null){
            return 0;
        }
        user.setPassword(Md5.getMD5String(user.getPassword()));

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
        //插入log表数据
        int logResult = ELogMapper.create(uid,vid,oid);

        if(logResult == 1){

            //option表对应选项number + 1

            int optionResult = EOptionMapper.updateNumber(vid,oid);
           if(optionResult == 1){

               return 1;
           }



        }



        return 0;
    }

    @Override
    public int haveVoted(Integer uid, Integer vid) {




        return ELogMapper.selectHaveVoted(uid,vid);
    }

    @Override
    public List<VoteVo> getVoteListLimit() {

        List<Vote>  voteList  = EVoteMapper.selectVoteListLimit();
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

    @Override
    public List<Option> getOptions(Integer vid) {


        return EOptionMapper.selectOptionListByVoteId(vid);
    }


//    @Override
//    public User getUserById(Integer id) {
//        return  EUserMapper.selectByPrimaryKey(id);
//    }
}
