package com.flow.client.controller;

import com.flow.bgd.model.User;
import com.flow.client.plugin.FriendJlabel;
import com.flow.client.util.ManagerChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class FriendList extends JFrame implements ActionListener, MouseListener {

    public static void main(String[] args) {
        //FriendList friendList = new FriendList();
    }


    //第一张卡片(好友)-分为三部分
    JPanel friend_jp1,friend_jp2, friend_jp3;
    JButton friend_jb1,friend_jb2,friend_jb3;

    //第二张卡片(陌生人)-分为三部分
    JPanel stranger_jp1,stranger_jp2, stranger_jp3;
    JButton stranger_jb1,stranger_jb2,stranger_jb3;

    //把整个JFrame设置成CardLayout
    CardLayout cardLayout;

    //好友列表
    private FriendJlabel[] friendLbs;


    //自己的账号
    private String selfId;

    public FriendList (String selfId, List<User> friends) {
        this.selfId = selfId;
    //第一张卡片
        /**点开后显示好友列表*/
        /** 添加按钮 */
        friend_jb1 = new JButton("我的好友");
        friend_jb2 = new JButton("陌生人");
        friend_jb2.addActionListener(this); // 对陌生人按钮就行监听
        friend_jb3 = new JButton("黑名单");
        /** 第一部分 */
        friend_jp1 = new JPanel(new BorderLayout());

        /** 第二部分->显示50个好友 */
        friend_jp2 = new JPanel(new GridLayout(50,1,4,4));
        //初始化50好友
        friendLbs = new FriendJlabel[friends.size()];
        for(int i=0;i<friends.size();i++) {
            User friend = friends.get(i);
            FriendJlabel friendJlabel = new FriendJlabel(friend,new ImageIcon("image/mm.jpg"),JLabel.LEFT);
            friendJlabel.setEnabled(friend.getOnline()==0?false:true);
            friendJlabel.addMouseListener(this);//添加鼠标事件
            friend_jp2.add(friendJlabel);
            friendLbs[i] = friendJlabel;
        }
        //滚动条
        JScrollPane friendScoll = new JScrollPane(friend_jp2);

        /** 第三部分->陌生人+黑名单 */
        friend_jp3 = new JPanel(new GridLayout(2, 1));
        friend_jp3.add(friend_jb2);
        friend_jp3.add(friend_jb3);

        /** 总 friend_jp1 布局 */
        friend_jp1.add(friend_jb1,"North");
        friend_jp1.add(friendScoll,"Center");
        friend_jp1.add(friend_jp3,"South");

        //第二张卡片
        /**点开后显示陌生人列表*/
        /** 添加按钮 */
        stranger_jb1 = new JButton("我的好友");
        stranger_jb1.addActionListener(this);//对"我的好友"添加监听
        stranger_jb2 = new JButton("陌生人");
        stranger_jb3 = new JButton("黑名单");
        /** 第一部分->陌生人+黑名单 */
        stranger_jp3 = new JPanel(new GridLayout(2, 1));
        stranger_jp3.add(stranger_jb1);
        stranger_jp3.add(stranger_jb2);

        /** 第二部分->显示20个陌生人 */
        stranger_jp2 = new JPanel(new GridLayout(20,1,4,4));
        JLabel[] strangers = new JLabel[20];
        for(int i=0;i<strangers.length;i++) {
            JLabel label = strangers[i];
            label =new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
            label.addMouseListener(this);//添加鼠标事件
            stranger_jp2.add(label);
        }
        //滚动条
        JScrollPane strangerScoll = new JScrollPane(stranger_jp2);

        /** 总 stranger_jp1 布局 */
        stranger_jp1 = new JPanel(new BorderLayout());
        stranger_jp1.add(stranger_jp3,"North");
        stranger_jp1.add(strangerScoll,"Center");
        stranger_jp1.add(stranger_jb3,"South");

        /** 把整个JFrame设置成CardLayout */
        cardLayout = new CardLayout();
        this.setTitle(selfId);//title 显示自己的编号
        this.setLayout(cardLayout);
        this.add(friend_jp1,"1");
        this.add(stranger_jp1,"2");
        this.setSize(150,500);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent action) {
        if (action.getSource() == friend_jb2) {
            // 监听第一张卡片的"陌生人"按钮
            cardLayout.show(this.getContentPane(),"2");
        } else if (action.getSource() == stranger_jb1) {
            // 监听第二张卡片的"好友"按钮
            cardLayout.show(this.getContentPane(),"1");
        }
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        Object source = event.getSource();
        if (source instanceof FriendJlabel) {
            FriendJlabel jLabel = (FriendJlabel) source;
            //双击事件
            if (event.getClickCount() == 2) {
                //得到好友编号
                String friendId = jLabel.getId();
                System.out.println("你["+selfId+"]准备和【"+friendId+"】聊天...");
                Chat chat = new Chat(selfId,friendId);
                ManagerChat.set(selfId+" "+friendId,chat);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    // 设置鼠标放入头像时，高亮
    @Override
    public void mouseEntered(MouseEvent event) {
        Object source = event.getSource();
        if (source instanceof JLabel) {
            JLabel jLabel = (JLabel) source;
            jLabel.setForeground(Color.red);
        }
    }
    // 设置鼠标移出头像时，恢复
    @Override
    public void mouseExited(MouseEvent event) {
        Object source = event.getSource();
        if (source instanceof JLabel) {
            JLabel jLabel = (JLabel) source;
            jLabel.setForeground(Color.black);
        }
    }

    /**
     * 更新好友列表
     */
    public void updateFriendList(String friendLstContent) {
        for (int i = 0; i <friendLbs.length ; i++) {
            if (friendLbs[i].getId().equals(friendLstContent))
            friendLbs[i].setEnabled(true);
        }
    }
}
