package com.flow.client.dao;

import com.flow.bgd.model.User;

import java.util.List;

public interface ClientUserMapper {
    public List<User> selectFriendsByUserId(String userId);
}
