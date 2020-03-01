package com.flow.bgd.service;

import com.flow.bgd.mapper.UserFriendMapper;
import com.flow.bgd.mapper.UserMapper;
import com.flow.bgd.model.User;
import com.flow.bgd.model.UserExample;
import com.flow.bgd.model.UserFriend;
import com.flow.bgd.model.UserFriendExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserFriendService implements IUserFriendService{

    @Resource
    private UserFriendMapper mapper;

    @Override
    public List<UserFriend> select(UserFriend select) {
        UserFriendExample example = new UserFriendExample();
        UserFriendExample.Criteria criteria = example.createCriteria();
        if (null != select.getUserId() && !"".equals(select.getUserId())) {
            criteria.andUserIdEqualTo(select.getUserId());
        }
        List<UserFriend> friends = mapper.selectByExample(example);
        return friends;
    }

}
