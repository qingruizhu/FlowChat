package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;
import com.qq.common.MessageType;

import javax.swing.*;

import java.awt.Color;
import java.awt.event.*;
import java.io.ObjectOutputStream;

public class AccepAdd  extends JFrame implements ActionListener{
	JButton yes,no,exit,min;
	Message ms;
	public AccepAdd(Message m) {
		this.ms = m;
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ms.getSender()+" 想加你为好友");
		lblNewLabel.setBounds(30, 53, 146, 33);
		getContentPane().add(lblNewLabel);
		
		yes = new JButton("同意");
		yes.addActionListener(this);
		yes.setBounds(24, 96, 66, 23);
		yes.setOpaque(true);
		yes.setBackground(Color.white);
		getContentPane().add(yes);
		
		no = new JButton("拒绝");
		no.addActionListener(this);
		no.setBounds(110, 96, 66, 23);
		no.setOpaque(true);
		no.setBackground(Color.white);
		getContentPane().add(no);
		
		exit = new JButton(new ImageIcon("image/fexit.png"));
		exit.setBounds(174, 0, 26, 26);
		getContentPane().add(exit);
		
		min = new JButton(new ImageIcon("image/fmin.png"));
		min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		min.setBounds(148, 0, 26, 26);
		getContentPane().add(min);
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("image/beijing.png"));
		lblNewLabel_1.setBounds(0, 0, 200, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(0, 26, 200, 122);
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.white);
		getContentPane().add(lblNewLabel_2);
		
		this.setUndecorated(true);
	
		this.setBounds(1166, 574, 200, 150);
		
		this.setVisible(true);
		
		this.setTitle("好友请求");
	}

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		AccepAdd m = new AccepAdd(new Message());
	}

	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()==yes)
		{
			
			QqFriendList list = ManageQqFriendList.getQqFriendList(ms.getGetter());
			if(list!=null)
				list.init_update(ms);
			//如果用户点击了，发送按钮
			Message m=new Message();
			m.setMesType(MessageType.message_acceptadd);
			m.setSender(ms.getGetter());
			m.setGetter(ms.getSender());
			m.setCon("yes");
			m.setSendTime(new java.util.Date().toString());
			//发送给服务器.
			try {
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(ms.getGetter()).getS().getOutputStream());
					oos.writeObject(m);
			} catch (Exception e1) {
				e1.printStackTrace();
				// TODO: handle exception
			}	
			this.dispose();
		}
		if(e.getSource()==no)
		{
			
			Message m=new Message();
			m.setMesType(MessageType.message_jujueadd);
			m.setSender(ms.getGetter());
			m.setGetter(ms.getSender());
			m.setCon("yes");
			m.setSendTime(new java.util.Date().toString());
			//发送给服务器.
			try {
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(ms.getGetter()).getS().getOutputStream());
					oos.writeObject(m);
			} catch (Exception e1) {
				e1.printStackTrace();
				// TODO: handle exception
			}	
			this.dispose();
		}
	}
}
