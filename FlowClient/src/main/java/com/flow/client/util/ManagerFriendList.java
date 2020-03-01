package com.flow.client.util;

import com.flow.client.controller.FriendList;

import java.util.HashMap;
import java.util.Map;

public class ManagerFriendList {

    private static Map<String, FriendList> container = new HashMap<String, FriendList>();

    public static void set(String selfId, FriendList friendList) {
        container.put(selfId, friendList);
    }
    public static FriendList get(String selfId) {
        return container.get(selfId);
    }
}
