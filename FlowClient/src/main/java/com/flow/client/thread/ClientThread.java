package com.flow.client.thread;

import com.flow.client.controller.Chat;
import com.flow.client.controller.FriendList;
import com.flow.client.model.ClientConServer;
import com.flow.client.util.ManagerChat;
import com.flow.client.util.ManagerFriendList;
import com.flow.common.Message;
import com.flow.common.MessageType;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true) {
            try {
                //客户端从服务端读取信息,读不到就一直等待
                ObjectInputStream ois = new ObjectInputStream(this.socket.getInputStream());
                Message message = (Message)ois.readObject();

                if (message.getMesType().equals(MessageType.message_comm_mes)) {
                    //显示在聊天框中
                    String content = message.getSender() + " 对 " + message.getGetter() + " 说：" + message.getCon() + "\r\n";
                    Chat chat = ManagerChat.get(message.getGetter() + " " + message.getSender());
                    chat.showMessage(content);
                } else if (message.getMesType().equals(MessageType.message_ret_onLineFriend)) {
                    String content = message.getCon();
                    System.out.println("好友上线通知："+content);
                    FriendList friendList = ManagerFriendList.get(message.getGetter());
                    friendList.updateFriendList(content.trim());
                }
            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }


    public Socket getSocket() {
        return socket;
    }

}
