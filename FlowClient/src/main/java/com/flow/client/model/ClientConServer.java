package com.flow.client.model;

import com.flow.bgd.model.User;
import com.flow.client.thread.ClientThread;
import com.flow.client.util.ManagerClientThread;
import com.flow.common.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConServer {

    public static boolean sendLoginInfoToServer(User user){
        boolean b = false;
        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message)ois.readObject();
            if (message.getMesType().equals("1")) {//登录成功
                //创建一个该qq号和服务器端保持通讯连接得线程
                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();
                ManagerClientThread.set(user.getUserId(), clientThread);
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}
