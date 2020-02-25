package com.flow.server.model;

import java.util.HashMap;

public class ManagerClientThread {

    public static HashMap manager = new HashMap<String,ServerConClientThread>();

    //添加客户端通讯线程
    public static void addClientThread(String userId, ServerConClientThread clientThread) {
        manager.put(userId, clientThread);
    }
    //添加客户端通讯线程
    public static ServerConClientThread getClientThread(String userId) {
       return (ServerConClientThread)manager.get(userId);
    }

}
