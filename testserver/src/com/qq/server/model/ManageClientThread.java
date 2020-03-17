package com.qq.server.model;

import java.net.InetAddress;
import java.util.*;
public class ManageClientThread {
	
	public static HashMap<String, SerConClientThread> hm=new HashMap<String, SerConClientThread>();
	public static HashMap<String, InetAddress> hm2=new HashMap<String, InetAddress>();
	//向hm中添加一个客户端通讯线程
	public static  void addClientThread(String uid,SerConClientThread ct)
	{
		hm.put(uid, ct);
	}
	
	public static SerConClientThread getClientThread(String uid)
	{
		return (SerConClientThread)hm.get(uid);
	}
	
	public static  void RemoveClientThread(String uid)
	{
		hm.remove(uid);
	}
	
	//////////////////////////////
	public static  void addClientip(String uid,InetAddress ip)
	{
		hm2.put(uid, ip);
	}
	public static InetAddress getClientip(String uid)
	{
		return (InetAddress)hm2.get(uid);
	}
	public static  void RemoveClientip(String uid)
	{
		hm2.remove(uid);
	}
	/////////////////////////////////////
	
	
	//返回当前在线的人的情况
	public static String getAllOnLineUserid()
	{
		//使用迭代器完成
		Iterator it=hm.keySet().iterator();
		String res="";
		while(it.hasNext())
		{
			res+=it.next().toString()+" ";
		}
		return res;
	}
}
