package com.flow.server.dao;

import com.flow.bgd.model.ChatContent;

import java.util.List;

public interface ServerChatContentMapper {
    public List<ChatContent> selectOutlineChatContent(String userId,String sender);
}
