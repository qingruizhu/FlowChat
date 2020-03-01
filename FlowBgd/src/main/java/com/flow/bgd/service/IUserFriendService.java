package com.flow.bgd.service;

import com.flow.bgd.model.User;
import com.flow.bgd.model.UserFriend;

import java.util.List;

public interface IUserFriendService {
    public List<UserFriend> select(UserFriend select);
}
