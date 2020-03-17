package com.flow.client.model;

import com.flow.bgd.model.User;
import com.flow.client.controller.FriendList;
import com.flow.client.dao.ClientUserMapper;
import com.flow.client.thread.ClientThread;
import com.flow.client.util.ManagerClientThread;
import com.flow.client.util.ManagerFriendList;
import com.flow.common.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

@Component
public class ClientConServer {

    @Resource
    private ClientUserMapper clientUserMapper;

    public  boolean sendLoginInfoToServer(User user){
//        List<User> aa = clientUserMapper.selectFriendsByUserId(user.getUserId());
//        System.out.println(aa.get(0));

        boolean b = false;
        try {

            Socket socket = new Socket("127.0.0.1", 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(user);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message)ois.readObject();
            if (message.getMesType().equals("1")) {//登录成功
                //创建好友列表
                List<User> friends = clientUserMapper.selectFriendsByUserId(user.getUserId());
                FriendList friendList = new FriendList(user.getUserId(),friends);
                ManagerFriendList.set(user.getUserId(),friendList);
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
