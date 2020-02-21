package com.flow.client.controller;

import javafx.scene.control.PasswordField;

import javax.swing.*;
import java.awt.*;

/**
 * 登录界面
 */
public class Login extends JFrame{

    public static void main(String[] args) {
        Login login = new Login();
    }


    public Login() {
        /** 上 */
        JLabel uper = new JLabel(new ImageIcon("image/tou.gif"));

        /** 中 */
        JLabel account = new JLabel("账号", JLabel.CENTER);
        JLabel pwd = new JLabel("密码", JLabel.CENTER);
        JTextField accoutFiled = new JTextField();
        JPasswordField pwdFiled = new JPasswordField();
        //控件放入面板中
        JPanel middler = new JPanel(new GridLayout(3,2));
        middler.add(account);
        middler.add(accoutFiled);
        middler.add(pwd);
        middler.add(pwdFiled);

        /** 下 */
        JButton yes = new JButton(new ImageIcon("image/denglu.gif"));
        JButton no = new JButton(new ImageIcon("image/quxiao.gif"));
        JPanel lower = new JPanel();
        lower.add(yes);
        lower.add(no);

        /** 定位 */
        this.add(uper,"North");
        this.add(middler,"Center");
        this.add(lower,"South");
        //设置
        this.setSize(350, 240);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
