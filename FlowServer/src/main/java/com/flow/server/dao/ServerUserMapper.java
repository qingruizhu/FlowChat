package com.flow.server.dao;

import com.flow.bgd.model.User;

import java.util.List;

public interface ServerUserMapper {
    public List<User> selectOnlineFriendsByUserId(String userId);
}
