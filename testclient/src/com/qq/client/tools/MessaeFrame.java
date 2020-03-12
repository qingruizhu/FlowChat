package com.qq.client.tools;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.qq.client.view.AccepAdd;
import com.qq.client.view.QqChat;
import com.qq.client.view.QunChat;
import com.qq.common.Message;
import com.qq.common.MessageType;

public class MessaeFrame extends JFrame implements ActionListener,MouseListener{

	JLabel[] jla=new JLabel[15];
	String owner;
	String friendNO;
	int x,y,width,higth=0,record=0;
	Message ms=new Message();
	JFileChooser fileDialog;
	File file;
	int flag;
	
	MessaeFrame(String own){
		owner=own;
		x=1100;y=700-higth;width=100;
		this.setUndecorated(true);
		flag=0;
		/*this.setBounds(x,y, width, higth);
		
		this.setLayout(new GridLayout(10,1));
		for(int i=0;i<record;i++)
		{
			jla[i]=new JLabel(""+i);
			jla[i].addMouseListener(this);
			this.add(jla[i]);
		}*/
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//QqChat qqChat=new QqChat("1");
		MessaeFrame f=new MessaeFrame("dsf");
		f.addJLabel("kl", "d");f.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getClickCount() == 2) {

			if (ms.getMesType().equals(MessageType.message_ret_pointMessage)) {
				String friendNo = ((JLabel) arg0.getSource()).getText();
				// System.out.println("你希望和 "+friendNo+" 聊天");
				QqChat qqChat = new QqChat(this.owner, friendNo);

				ManageQqChat.addQqChat(this.owner + " " + friendNo, qqChat);

				// /////////////////////////////////////////////////////
				Message m = new Message();
				m.setMesType(MessageType.message_ret_outlineMessage);
				m.setSender(this.owner);
				m.setGetter(friendNo);
				// m.setCon(jtf.getText());
				m.setSendTime(new java.util.Date().toString());
				// 发送给服务器.
				try {
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread
									.getClientConServerThread(owner).getS()
									.getOutputStream());
					oos.writeObject(m);
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
					// flag+=friendNo;

				}

			}
			if (ms.getMesType().equals(MessageType.message_sendfile)) {
				if (flag == 0) {
					fileDialog = new JFileChooser();
					fileDialog.showSaveDialog(this);
					file = fileDialog.getSelectedFile();
				}
				try {
					FileWriter filewrite = new FileWriter(file);
					BufferedWriter buffwrite = new BufferedWriter(filewrite);
					buffwrite.write(ms.getCon());
					buffwrite.newLine();
					filewrite.close();
					buffwrite.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag++;
			}
			if (ms.getMesType().equals(MessageType.message_comm_mes)) {
				QqChat qqChat1 = new QqChat(this.owner, ms.getSender());
				
				ManageQqChat.addQqChat(this.owner + " " + ms.getSender(), qqChat1);
				//qqChat1.setVisible(true);
				qqChat1.showMessage(ms);

			}
			if (ms.getMesType().equals(MessageType.message_addpoint)) {
				new AccepAdd(ms);

			}
			if (ms.getMesType().equals(MessageType.message_hasfriendask)) {
				new AccepAdd(ms);

			}
			if (ms.getMesType().equals(MessageType.message_Qun_mes)) {
                QunChat qqChat1 = new QunChat(this.owner, ms.getSender());
				
				ManageQqChat.addQunChat(this.owner + "  " + ms.getSender(), qqChat1);
				//qqChat1.setVisible(true);
				qqChat1.showMessage(ms);
			}

			deleteJLabel(arg0.getComponent());

			// ////////////////////////////////////////////////

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
		//friendNO=text1;
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
	public void setMs(Message m)
	{
		ms=m;
	}

}
