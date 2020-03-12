package com.flow.server.controller;


import com.flow.bgd.mapper.UserMapper;
import com.flow.bgd.model.User;
import com.flow.bgd.model.UserFriend;
import com.flow.bgd.service.UserFriendService;
import com.flow.bgd.service.UserService;
import com.flow.common.Message;
import com.flow.common.MessageType;
import com.flow.server.dao.ServerUserMapper;
import com.flow.server.model.ManagerClientThread;
import com.flow.server.model.ServerConClientThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

/**
 * 聊天服务器：监听，等待客户端连接
 */
@Component
public class Server {

    @Autowired
    private UserService userService;
    @Resource
    ServerUserMapper serverUserMapper;

    public void listening() {
        try {
            System.out.println("服务器，在9999开始监听...");
            //监听
            ServerSocket server = new ServerSocket(9999);
            while (true) {
                //阻塞等待连接
                Socket accept = server.accept();
                ObjectInputStream ois = new ObjectInputStream(accept.getInputStream());
                User user = (User) ois.readObject();
                Message message = new Message();
                ObjectOutputStream oos = new ObjectOutputStream(accept.getOutputStream());
                User select = userService.select(user);
                if (null!=select && select.getPassword().equals(user.getPassword())) {
//                if ("123456".equals(user.getPassword())) {
                    /** 1.返回登录成功 */
                    message.setMesType(MessageType.message_succeed);
                    oos.writeObject(message);
                    /** 2.修改登录状态*/
                    User update = new User();
                    update.setId(select.getId());
                    update.setOnline(1);
                    userService.update(update);
                    /** 3.开启一个线程与客户端保持通讯 */
                    ServerConClientThread thread = new ServerConClientThread(accept);
                    ManagerClientThread.set(user.getUserId(), thread);
                    thread.start();//启动与该客户端通讯的线程
                    /** 4.通知其好友自己已上线 */
                    List<User> users = serverUserMapper.selectOnlineFriendsByUserId(user.getUserId());
                    if (null!=users && users.size()>0) {
                        Message ms = new Message();
                        ms.setCon(user.getUserId());
                        ms.setMesType(MessageType.message_ret_onLineFriend);
                        for (User friend : users) {
                                ms.setGetter(friend.getUserId());
                                Socket socket = ManagerClientThread.get(friend.getUserId()).getSocket();
                                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                                outputStream.writeObject(ms);
                        }
                    }
                } else {
                    //失败->关闭连接
                    message.setMesType(MessageType.message_login_fail);
                    oos.writeObject(message);
                    accept.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
