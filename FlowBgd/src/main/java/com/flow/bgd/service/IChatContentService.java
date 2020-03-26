package com.flow.bgd.service;

import com.flow.bgd.model.ChatContent;

import java.util.List;

public interface IChatContentService {
    List<ChatContent> select(ChatContent content);
    int insert(ChatContent content);
}
