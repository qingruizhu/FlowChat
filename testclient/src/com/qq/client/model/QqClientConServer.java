/**
 * ���ǿͻ������ӷ������ĺ�̨
 */
package com.qq.client.model;
import com.qq.client.tools.ClientConServerThread;
import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
public class QqClientConServer {

	
	public  Socket s;
	
	//���͵�һ������
	public Message sendLoginInfoToServer(Object o)
	{
		Message m = null;
		try {
			s=new Socket("127.0.0.1",9999);
			//s=new Socket("10.100.56.68",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			
			Message ms=(Message)ois.readObject();
			//���������֤�û���¼�ĵط�
			if(ms.getMesType().equals("1"))
			{
				//�ʹ���һ����qq�źͷ������˱���ͨѶ���ӵ��߳�
				ClientConServerThread ccst=new ClientConServerThread(s);
				//������ͨѶ�߳�
				ccst.start();
				ManageClientConServerThread.addClientConServerThread
				(((User)o).getUserId(), ccst);
				m = ms;
			}
			else{
				//�ر�Scoket
				s.close();
				m =ms;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
		return m;
	}
	
	public boolean sendunLoadInfoToServer(Object o)
	{
		boolean b=false;
		try {
			s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
		return b;
	}
	
	public Message sendRegisteInfoToServer(Object o)
	{
		Message m = null;
		try {
			//s=new Socket("127.0.0.1",9999);
			s=new Socket("127.0.0.1",9999);
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
			
			ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
			
			Message ms=(Message)ois.readObject();
			//���������֤�û���¼�ĵط�
			if(ms.getMesType().equals("4"))
			{
				m = ms;
			}
			else{
				//�ر�Scoket
				s.close();
				m =ms;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}
		return m;
	}
	
	public void SendInfoToServer(Object o)
	{
		/*try {
			Socket s=new Socket("127.0.0.1",9999);
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}finally{
			
		}*/
	}
}
