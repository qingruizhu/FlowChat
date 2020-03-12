/**
 * 我的好友列表,(也包括陌生人，黑名单)
 */
package com.qq.client.view;

import com.qq.client.model.QqClientUser;
import com.qq.client.tools.*;
import com.qq.common.Message;
import com.qq.common.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class QqFriendList extends JFrame implements ActionListener,MouseListener,WindowListener{

	//处理第一张卡片.
	
	private static final String JLabel = null;
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3,jphy_addnewfriend;
	JScrollPane jsp1;
	String owner;
	//处理第二张卡片(陌生人).
	
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel jb1s;
	
	HashMap friendlist=new HashMap<String, JLabel>();
	//把整个JFrame设置成CardLayout
	CardLayout cl;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	QqFriendList qqFriendList=new QqFriendList();
	}
	
	//更新在线的好友情况
	public void upateFriend(Message m)
	{
		JLabel lab;
		String onLineFriend[]=m.getCon().split(" ");
		JOptionPane.showMessageDialog(this,this.owner);
		for(int i=0;i<onLineFriend.length;i++)
		{			
		//	jb1s[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		//	friendlist.get(onLineFriend[i]).
			lab = (JLabel)friendlist.get("s");
			lab.setEnabled(true);
		}
	}
	
	public void upateunloadFriend(Message m)
	{
		String onLineFriend[]=m.getCon().split(" ");
		JLabel lab;
		for(int i=0;i<onLineFriend.length;i++)
		{
			
	//		jb1s[Integer.parseInt(onLineFriend[i])-1].setEnabled(false);
			lab = (JLabel)friendlist.get("s");
			lab.setEnabled(true);
		}
	}
	
	
	public void init_update(String ownerId,Message m)
	{
		JLabel jlab;
		jlab=new JLabel(m.getCon().toString()+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
		jlab.setEnabled(false);
		
		jlab.addMouseListener(this);
		friendlist.put(m.getCon().toString(),jlab);
		jphy2.add(jlab);
		jphy2.repaint();
	}
	
	public QqFriendList(String ownerId,Message m)
	{
		
		addWindowListener(this);
		this.owner=ownerId;
		//处理第一张卡片(显示好友列表)
		jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("黑名单");
		jphy1=new JPanel(new BorderLayout());
		jphy_addnewfriend = new JButton("添加好友");
		jphy_addnewfriend.addActionListener(this);
		String friend[]=m.getCon().split(" ");


		if(friend.length < 10)
			jphy2=new JPanel(new GridLayout(10,1,4,4));
		else
			jphy2=new JPanel(new GridLayout(friend.length,1,4,4));
		//给jphy2，初始化好友.
		
		
		for(int i=0;i<friend.length;i++)
		{
			jb1s=new JLabel(friend[i].toString()+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jb1s.setEnabled(false);
			if(jb1s.getText().equals(ownerId))
			{
				jb1s.setEnabled(true);
			}
			jb1s.addMouseListener(this);
			friendlist.put(friend[i].toString(),jb1s);
			jphy2.add(jb1s);
			
			
		}
		
		jphy3=new JPanel(new GridLayout(3,1));
		//把两个按钮加入到jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jphy3.add(jphy_addnewfriend);
		
		jsp1=new JScrollPane(jphy2);
		
		
		//对jphy1,初始化
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		
		
		//处理第二张卡片
		
		
		jpmsr_jb1=new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("陌生人");
		jpmsr_jb3=new JButton("黑名单");
		jpmsr1=new JPanel(new BorderLayout());
		//假定有20个陌生人
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		
		//给jphy2，初始化20陌生人.
		JLabel []jb1s2=new JLabel[20];
		
		for(int i=0;i<jb1s2.length;i++)
		{
			jb1s2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jpmsr2.add(jb1s2[i]);
		}
		
		jpmsr3=new JPanel(new GridLayout(2,1));
		//把两个按钮加入到jphy3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		
		
		jsp2=new JScrollPane(jpmsr2);
		
		
		//对jphy1,初始化
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		
		
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1");
		this.add(jpmsr1,"2");
		//在窗口显示自己的编号.
		this.setTitle(ownerId);
		this.setSize(140, 400);
		this.setVisible(true);
		
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//如果点击了陌生人按钮，就显示第二张卡片
		if(arg0.getSource()==jphy_jb2)
		{
			cl.show(this.getContentPane(), "2");
		}else if(arg0.getSource()==jpmsr_jb1){
			cl.show(this.getContentPane(), "1");
		}
		
		if(arg0.getSource()==jphy_addnewfriend)
		{
			new NewFriend(this.owner);
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//响应用户双击的事件，并得到好友的编号.
		if(arg0.getClickCount()==2)
		{
			//得到该好友的编号
			String friendNo=((JLabel)arg0.getSource()).getText();
			//System.out.println("你希望和 "+friendNo+" 聊天");
			QqChat qqChat=new QqChat(this.owner,friendNo);
			
			//把聊天界面加入到管理类
			ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);
			
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.red);
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		JLabel jl=(JLabel)arg0.getSource();
		jl.setForeground(Color.black);
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void windowOpened(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void windowClosing(WindowEvent e) {
		// TODO 自动生成的方法存根
		QqClientUser qqClientUser=new QqClientUser();
		User u=new User();
		u.setUserId(this.owner);
		u.setUnload("1234567");
		u.setPasswd("2");
		qqClientUser.unload(u);
		
		
	/*	System.exit(0);*/
	}

	public void windowClosed(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void windowIconified(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void windowDeiconified(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void windowActivated(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void windowDeactivated(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

}
