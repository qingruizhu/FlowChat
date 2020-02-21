package com.flow.client.controller;

import javax.swing.*;

public class Chat extends JFrame{

    public static void main(String[] args) {
        Chat qqChat=new Chat("1","zqr");
    }


    public Chat(String selfId,String friendNo){
        JTextArea area = new JTextArea();
        JTextField filed = new JTextField(15);
        JButton button = new JButton("发送");
        JPanel panel = new JPanel();
        panel.add(filed);
        panel.add(button);

        this.add(area,"Center");
        this.add(panel, "South");
        this.setTitle(selfId+" 正在和 "+friendNo+" 聊天");
        this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
        this.setSize(300, 200);
        this.setVisible(true);
    }
}
