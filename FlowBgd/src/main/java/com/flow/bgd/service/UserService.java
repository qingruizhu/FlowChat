package com.flow.bgd.service;

import com.flow.bgd.mapper.UserMapper;
import com.flow.bgd.model.User;
import com.flow.bgd.model.UserExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Resource
    private UserMapper mapper;

    @Override
    public User select(User select) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (null != select.getUserId() && !"".equals(select.getUserId())) {
            criteria.andUserIdEqualTo(select.getUserId());
        }
        List<User> users = mapper.selectByExample(example);
        if ( !CollectionUtils.isEmpty(users)) {
            return users.get(0);
        }
        return null;
    }

}
