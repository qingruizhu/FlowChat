package com.qq.client.view;
import com.qq.client.tools.*;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.qq.client.view.QqChat;
import com.qq.common.Message;
import com.qq.common.MessageType;

public class MessaeFrame extends JFrame implements ActionListener,MouseListener{

	JLabel[] jla=new JLabel[15];
	String owner;
	String friendNO;
	Message ms;
	public Message getMs() {
		return ms;
	}
	public void setMs(Message ms) {
		this.ms = ms;
	}
	int x,y,width,higth=0,record=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	public MessaeFrame(String own){
		owner=own;
		this.ms = ms;
		x=1100;y=700-higth;width=100;
		this.setUndecorated(true);
		/*this.setBounds(x,y, width, higth);
		
		this.setLayout(new GridLayout(10,1));
		for(int i=0;i<record;i++)
		{
			jla[i]=new JLabel(""+i);
			jla[i].addMouseListener(this);
			this.add(jla[i]);
		}*/
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getClickCount()==2)
		{
			String friendNo=((JLabel)arg0.getSource()).getText();
			if(ms.getMesType().equals(MessageType.message_ret_pointMessage))
			{
			friendNo=((JLabel)arg0.getSource()).getText();
			//System.out.println("你希望和 "+friendNo+" 聊天");
			QqChat qqChat=new QqChat(this.owner,friendNo);
			
			ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);

	///////////////////////////////////////////////////////
			//if(flag.indexOf(friendNo)==-1)
			{
				System.out.print("lldl");
			    Message m=new Message();
			    m.setMesType(MessageType.message_ret_outlineMessage);
			    m.setSender(this.owner);
			    m.setGetter(friendNo);
			    //m.setCon(jtf.getText());
			    m.setSendTime(new java.util.Date().toString());
			    //发送给服务器.
			    try {
				      ObjectOutputStream oos=new ObjectOutputStream
				      (ManageClientConServerThread.getClientConServerThread(owner).getS().getOutputStream());
				      oos.writeObject(m);
			     } catch (Exception e) {
				 e.printStackTrace();
				 // TODO: handle exception
				// flag+=friendNo;
			}
			}
			
		}
			if(ms.getMesType().equals(MessageType.message_comm_mes))
			{
				QqChat qqChat1=new QqChat(this.owner,ms.getSender());
				
				qqChat1.setVisible(true);
				qqChat1.showMessage(ms);
			
			}

			if(ms.getMesType().equals(MessageType.message_addpoint))
			{
				String s = ms.getGetter();
				ms.setGetter(ms.getSender());
				ms.setSender(s);
				new AccepAdd(ms);
			}
			if(ms.getMesType().equals(MessageType.message_hasfriendask))
			{
				
				new AccepAdd(ms);
			}
		    deleteJLabel(arg0.getComponent());
	//////////////////////////////////////////////////		
			
		}
	}
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void addJLabel(String text,String text1)
	{
		higth+=30;
		record++;
		owner=text;
		boolean flag=false;
		//this.removeAll();
			
		
		for(int i=0;i<record-1;i++)
		{
			flag=jla[i].getText().equals(text1);
		}
		if(!flag)
		{
			jla[record-1]=new JLabel(text1,new ImageIcon("mm.jpg"),JLabel.LEFT);
			jla[record-1].addMouseListener(this);
			this.add(jla[record-1]);
		}
		else
		{
			higth-=30;
			record--;	
		}
		this.setLayout(new GridLayout(record,1));
		y=700-higth;
		this.setBounds(x,y, width, higth);
	}
	public void deleteJLabel(Component comp)
	{
		higth-=30;
		record--;	
		this.remove(comp);
		this.setLayout(new GridLayout(record,1));
		y=700-higth;
		this.setBounds(x,y, width, higth);
	}
	public int getRecord()
	{
		return record;
	}

}
