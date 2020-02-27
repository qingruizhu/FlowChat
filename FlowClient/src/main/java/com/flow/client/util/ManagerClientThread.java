package com.flow.client.util;

import com.flow.client.model.ClientConServer;
import com.flow.client.thread.ClientThread;

import java.util.HashMap;

public class ManagerClientThread {

    private static HashMap<String, ClientThread> manager =new HashMap<String,ClientThread>();

    public static void set(String userId, ClientThread clientThread) {
        manager.put(userId, clientThread);
    }
    public static ClientThread get(String userId) {
        return manager.get(userId);
    }
}
