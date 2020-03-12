/**
 * 这是客户端和服务器端保持通讯的线程.
 */
package com.qq.client.tools;

import com.qq.client.view.*;
import com.qq.common.Message;
import com.qq.common.MessageType;

import javax.swing.*;
import java.awt.*;
import java.io.ObjectInputStream;
import java.net.Socket;
public class ClientConServerThread extends Thread {

	private static final Component NULL = null;
	private Socket s;
	ReceveFrame receveframe;
	MessaeFrame ms=new MessaeFrame("1");
	//构造函数
	public ClientConServerThread(Socket s)
	{
		this.s=s;
		ms.setVisible(false);
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			//不停的读取从服务器端发来的消息
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
			/*	System.out.println("读取到从服务发来的消息"+ m.getSender() +" 给 "+m.getGetter()+" 内容"+
						m.getCon());*/
				
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{
				
					//把从服务器获得消息，显示到该显示的聊天界面
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					//显示
					if(qqChat!=null)
						qqChat.showMessage(m);
						if(qqChat==null)
						{
							if(m!=null)
							{	
								ms.setMs(m);
								ms.addJLabel(m.getGetter(),m.getSender());	
								ms.setVisible(true);						
							}
						}

				}else if(m.getMesType().equals(MessageType.message_ret_onLineFriend))
				{
					String con=m.getCon();
					String friends[]=con.split(" ");
					String getter=m.getGetter();
					//修改相应的好友列表.
					QqFriendList qqFriendList=ManageQqFriendList.getQqFriendList(getter);
					
				//	if(qqFriendList)
					//更新在线好友.
					if(qqFriendList!=null)
					{
						qqFriendList.upateFriend(m);
					}
				}
				else if(m.getMesType().equals(MessageType.messgae_ret_unloadFriends))
				{
					String con=m.getCon();
					String friends[]=con.split(" ");
					String getter=m.getGetter();
					//修改相应的好友列表.
					QqFriendList qqFriendList=ManageQqFriendList.getQqFriendList(getter);
					
				//	if(qqFriendList)
					//更新在线好友.
					if(qqFriendList!=null)
					{
						qqFriendList.upateunloadFriend(m);;
					}
				}
				else if(m.getMesType().equals(MessageType.message_ret_addFriend))
				{
					String con=m.getCon();
					if(con == null)
					{	
						JOptionPane.showMessageDialog(NULL, "没有该用户");
					}
					else
					{
					/*	QqFriendList qqList = ManageQqFriendList.getQqFriendList(m.getGetter());
						qqList.init_update(m.getGetter(), m.getSender());
						qqList = ManageQqFriendList.getQqFriendList("3");
						if(qqList!=null){
							JOptionPane.showMessageDialog(qqList,m.getSender()+"sss");
							qqList.init_update(m.getSender(), m.getGetter());
						}
						//发送一个要求返回在线好友的请求包.
						ObjectOutputStream oos=new ObjectOutputStream
						(ManageClientConServerThread.getClientConServerThread(m.getGetter()).getS().getOutputStream());
						
						Message m2=new Message();
						m2.setMesType(MessageType.message_get_onLineFriend);
						//指明我要的是这个qq号的好友情况.
						m2.setSender(m.getGetter());
						oos.writeObject(m2);
						m2.setSender(m.getSender());
						oos.writeObject(m2);*/
					}
			
				}
				else if(m.getMesType().equals(MessageType.message_acceptadd))
				{
					QqFriendList qqList = ManageQqFriendList.getQqFriendList(m.getGetter());
					if(qqList!=null){
						qqList.init_update(m);
					}
				}
				else if(m.getMesType().equals(MessageType.message_tiren))
				{
					QqFriendList qqList = ManageQqFriendList.getQqFriendList(m.getGetter());
					JOptionPane.showMessageDialog(NULL, m.getGetter()+" 您被服务器踢了");	
					qqList.dispose();
					ManageClientConServerThread.getClientConServerThread(m.getGetter()).stop();
					ManageClientConServerThread.RemoveClientConServerThread(m.getGetter());
					//ManageClientConServerThread.getClientConServerThread(m.getGetter()).s.close();
				}
				else if(m.getMesType().equals(MessageType.message_close))
				{
					QqFriendList qqList = ManageQqFriendList.getQqFriendList(m.getGetter());
					JOptionPane.showMessageDialog(qqList, " 对不起，服务器暂时关闭");	
					qqList.dispose();
					ManageClientConServerThread.getClientConServerThread(m.getGetter()).stop();
					ManageClientConServerThread.RemoveClientConServerThread(m.getGetter());
					//ManageClientConServerThread.getClientConServerThread(m.getGetter()).s.close();
				}
				else if(m.getMesType().equals(MessageType.message_jujueadd))
				{
					JOptionPane.showMessageDialog(NULL, m.getSender()+"拒绝了你的好友请求");
				}
				else if(m.getMesType().equals(MessageType.message_gonggao))
				{
					//弹窗，同意的话执行下面一句，并返回同意信息。
					new Gonggao(m);
				
				}
				else if(m.getMesType().equals(MessageType.message_ret_oldMessage))
				{
				
					//把从服务器获得消息，显示到该显示的聊天界面
					QqChat qqChat=ManageQqChat.getQqChat(m.getSender()+" "+m.getGetter());
					//显示
					
					qqChat.showoldMessage(m);
				}
				else if(m.getMesType().equals(MessageType.message_ret_outlineMessage))
				{
					QqChat qqChat=ManageQqChat.getQqChat(m.getSender()+" "+m.getGetter());
					//显示
					
					qqChat.showMessage(m);
				}
				else if(m.getMesType().equals(MessageType.message_ret_pointMessage))
				{
					if(m!=null)
					{
					//QqChat qqChat=ManageQqChat.getQqChat("sfs");					
					//显示	
						ms.setMs(m);
						ms.setVisible(true);
						ms.addJLabel(m.getSender(),m.getGetter());
					}
				}
				else if(m.getMesType().equals(MessageType.message_addpoint))
				{
					if(m!=null)
					{
					//QqChat qqChat=ManageQqChat.getQqChat("sfs");					
					//显示	
						System.out.println("m.setFriname "+m.getFriname());
						ms.setVisible(true);
						ms.setMs(m);
						ms.addJLabel(m.getGetter(),m.getSender());
					}
				}
				else if(m.getMesType().equals(MessageType.message_hasfriendask))
				{
					if(m!=null)
					{
					//QqChat qqChat=ManageQqChat.getQqChat("sfs");					
					//显示	
						ms.setVisible(true);
						ms.setMs(m);
						ms.addJLabel(m.getGetter(),m.getSender());
					}
				}
				else if(m.getMesType().equals(MessageType.message_shake))
				{
					
					QqChat qqChat=ManageQqChat.getQqChat(m.getGetter()+" "+m.getSender());
					//显示
					if(qqChat!=null)
						qqChat.shake(m);
				}
				else if(m.getMesType().equals(MessageType.message_sendfile))
				{
					if(receveframe ==null)
					{	
						receveframe = new ReceveFrame ();
						receveframe.setMs(m);
						receveframe.setVisible(true);
					}
					else
					{
						receveframe.setMs(m);
						receveframe.saveFile();						
						receveframe.setVisible(false);
						receveframe=null;
					}
				}
				///////////////////////返回个人资料
				else if(m.getMesType().equals(MessageType.message_data))
				{
					PersonalInfo personalinfo=new PersonalInfo(m.getSender());
					personalinfo.setTexts(m.getCon());
					personalinfo.setVisible(true);
				}
				else if(m.getMesType().equals(MessageType.message_fridata))
				{
					friendPersonalInfo personalinfo=new friendPersonalInfo(m.getSender());
					personalinfo.setTexts(m.getCon());
					personalinfo.setVisible(true);
				}
				////////////////////////////////
				else if(m.getMesType().equals(MessageType.message_ret_ip))
				{
					//弹窗，同意的话执行下面一句，并返回同意信息。
					AccepVideo accpvideo=new AccepVideo(m);
				}

				else if(m.getMesType().equals(MessageType.message_acceptvdo))
				{
					//弹窗，同意的话执行下面一句，并返回同意信息。
//					new VAplay(m.getMyport(),m.getFriendport(),m.getAddress());
				}
				else if(m.getMesType().equals(MessageType.message_jujuevdo))
				{
					//弹窗，同意的话执行下面一句，并返回同意信息。
					JOptionPane.showMessageDialog(NULL, m.getGetter()+"拒绝了你的视频请求");
				}
				else if (m.getMesType().equals(MessageType.message_Qun_mes)) {
					QunChat qunChat = ManageQqChat.getQunChat(m.getSender()+"  "
							+ m.getGetter());
					// 显示
					System.out.println(m.getSender()+"  "+m.getGetter());
					if (qunChat != null)
					{
						System.out.println(m.getSender()+"  "+m.getGetter());
						qunChat.showMessage(m);
					}
						
					if (qunChat == null) {
						if (m != null) {
							ms.setMs(m);
							ms.addJLabel(m.getGetter(), m.getSender());
							ms.setVisible(true);
						}
					}
				}
				else if (m.getMesType().equals(MessageType.message_Qun_oldmes))
				{
					QunChat qunChat = ManageQqChat.getQunChat(m.getSender()+"  "+m.getGetter());
					System.out.println(m.getSender()+"  "+m.getGetter());
					// 显示
                    if(qunChat==null)  
                    System.out.println("            lllll");
					qunChat.showoldMessage(m);
				}
				else if (m.getMesType().equals(MessageType.message_getQunPeople))
				{
					QunChat qunChat = ManageQqChat.getQunChat(m.getSender()+"  "+m.getGetter());
					qunChat.addJLabel(m.getGetter(), m.getCon());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
	
}
