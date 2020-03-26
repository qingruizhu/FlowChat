package com.flow.server.service;

import com.flow.bgd.model.ChatContent;
import com.flow.bgd.service.ChatContentService;
import com.flow.server.dao.ServerChatContentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Auther: qingruizhu
 * @Date: 2020/3/24 14:28
 */
@Service
public class ServerChatContentService extends ChatContentService {

    @Resource
    private ServerChatContentMapper serverChatContentMapper;

    /**
     * 查询离线信息
     */
    public List<ChatContent> selectOutlineChatContent(String userId,String sender){
        return serverChatContentMapper.selectOutlineChatContent(userId,sender);
    }
}
