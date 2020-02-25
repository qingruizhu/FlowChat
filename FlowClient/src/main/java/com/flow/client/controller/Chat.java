package com.flow.client.controller;

import com.flow.client.model.ClientConServer;
import com.flow.common.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

/**
 * 好友聊天界面：
 *      因为客户端处于读取状态，把他做成一个线程
 *
 */
public class Chat extends JFrame implements ActionListener,Runnable{

    public static void main(String[] args) {
        Chat qqChat=new Chat("1","zqr");
    }

    private JTextArea textArea;
    private JTextField sendContent;
    private JButton sendButton;
    private String selfId, friendId;


    public Chat(String selfId,String friendId){
        this.selfId = selfId;
        this.friendId = friendId;

        textArea = new JTextArea();
        sendContent = new JTextField(15);
        sendButton = new JButton("发送");
        sendButton.addActionListener(this);//监听发送按钮
        JPanel panel = new JPanel();
        panel.add(sendContent);
        panel.add(sendButton);

        this.add(textArea,"Center");
        this.add(panel, "South");
        this.setTitle(selfId+" 正在和 "+friendId+" 聊天");
        this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
        this.setSize(300, 200);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == sendButton) {
            /** 1.发送信息 */
            Message message = new Message();
            message.setSender(selfId);
            message.setGetter(friendId);
            message.setCon(sendContent.getText());
            message.setSendTime(new Date().toString());
            try {
                ObjectOutputStream oos = new ObjectOutputStream(ClientConServer.socket.getOutputStream());
                oos.writeObject(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                //客户端从服务端读取信息,读不到就一直等待
                ObjectInputStream ois = new ObjectInputStream(ClientConServer.socket.getInputStream());
                Message message = (Message)ois.readObject();
                //显示在聊天框中
                String content = message.getSender() + " 对 " + message.getGetter() + " 说：" + message.getCon() + "\r\n";
                textArea.append(content);

            } catch (Exception e) {
                e.printStackTrace();

            }

        }
    }
}
