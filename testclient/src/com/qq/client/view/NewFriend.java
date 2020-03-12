package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;
import com.qq.common.MessageType;
import javax.swing.*;

import java.awt.event.*;
import java.io.ObjectOutputStream;

public class NewFriend extends JFrame implements ActionListener{

	
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewFriend n = new NewFriend("1");
	}
	public NewFriend(String ownerId)
	{
		this.ownerId=ownerId;
//		this.friendId=friend;
//		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("添加");
		jb.addActionListener(this);
		jp=new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		
		this.add(jp,"North");
		this.setTitle("添加好友");
		this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
		this.setSize(300, 200);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==jb)
		{
			//如果用户点击了，发送按钮
			Message m=new Message();
			m.setMesType(MessageType.message_ret_addFriend);
			m.setSender(this.ownerId);
			m.setGetter(jtf.getText());
			m.setCon(jtf.getText());
			m.setSendTime(new java.util.Date().toString());
			//发送给服务器.
			try {
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
					oos.writeObject(m);
			} catch (Exception e1) {
				e1.printStackTrace();
				// TODO: handle exception
			}
			
			
		}
	}
	
	
}
