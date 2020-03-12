package com.qq.client.model;

import com.qq.common.*;
public class QqClientUser {

	public Message checkUser(User u)
	{
		return new QqClientConServer().sendLoginInfoToServer(u);
	}
	
	public boolean unload(User u)
	{
		return new QqClientConServer().sendunLoadInfoToServer(u);
	}
	public Message Registe(User u)
	{
		return new QqClientConServer().sendRegisteInfoToServer(u);
	}
}
