package com.flow.server.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerFrame extends JFrame implements ActionListener {


    public static void main(String[] args) {
        new ServerFrame();
    }

    private JButton start, stop;
    public ServerFrame(){
        System.out.println("aaa");
        JPanel panel = new JPanel();
        start=new JButton("启动服务器");
        start.addActionListener(this);
        stop=new JButton("关闭服务器");
        panel.add(start);
        panel.add(stop);

        this.add(panel);
        this.setSize(500, 400);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource()==start) {
            new Server();//开启服务器监听
        }

    }
}
