package com.flow.server.controller;


import com.flow.bgd.mapper.UserMapper;
import com.flow.bgd.model.User;
import com.flow.bgd.service.UserService;
import com.flow.common.Message;
import com.flow.server.model.ManagerClientThread;
import com.flow.server.model.ServerConClientThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 聊天服务器：监听，等待客户端连接
 */
@Component
public class Server {

    @Autowired
    private UserService userService;

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
//                if (null!=select && select.getPassword().equals(user.getPassword())) {
                if ("123456".equals(user.getPassword())) {
                    /** 1.返回登录成功 */
                    message.setMesType("1");
                    oos.writeObject(message);
                    /** 2.开启一个线程与客户端保持通讯 */
                    ServerConClientThread thread = new ServerConClientThread(accept);
                    ManagerClientThread.manager.put(user.getUserId(), thread);
                    thread.start();//启动与该客户端通讯的线程
                } else {
                    //失败->关闭连接
                    message.setMesType("2");
                    oos.writeObject(message);
                    accept.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
