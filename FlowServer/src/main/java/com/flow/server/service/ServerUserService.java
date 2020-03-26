package com.flow.server.service;

import com.flow.bgd.model.User;
import com.flow.bgd.service.UserService;
import com.flow.server.dao.ServerUserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description:
 * @Auther: qingruizhu
 * @Date: 2020/3/24 14:28
 */
@Service
public class ServerUserService extends UserService {

    @Resource
    private ServerUserMapper serverUserMapper;

    /**
     * 查询在线好友列表
     */
    public List<User> selectOnlineFriendsByUserId(String userId){
        return serverUserMapper.selectOnlineFriendsByUserId(userId);
    }
}
