package com.qq.common;

import java.net.InetAddress;

public class Message implements java.io.Serializable{

	private String mesType;

	private String sender;
	private String getter;
	private String con;
	private String sendTime;
	private String fenzucount;
	private String con1;
	private String font;
	private int size;
	private String col;
	private String file;//文件类型
    private byte[] by;//文件类容
    
    InetAddress address;
    private int myport;
    private int friendport;
    private String myname;
    private String friname;
    
	public String getMyname() {
		return myname;
	}

	public void setMyname(String myname) {
		this.myname = myname;
	}

	public String getFriname() {
		return friname;
	}

	public void setFriname(String friname) {
		this.friname = friname;
	}

	public int getMyport() {
		return myport;
	}

	public void setMyport(int myport) {
		this.myport = myport;
	}

	public int getFriendport() {
		return friendport;
	}

	public void setFriendport(int friendport) {
		this.friendport = friendport;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	public byte[] getFilebyte() {
		return by;
	}

	public void setFilebyte(byte[] b,int n) {
		
		by=new byte[n];
		by=b;
	}
	
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getCon1() {
		return con1;
	}

	public void setCon1(String con1) {
		this.con1 = con1;
	}

	public String getFenzucount() {
		return fenzucount;
	}

	public void setFenzucount(String fenzucount) {
		this.fenzucount = fenzucount;
	}
	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getCon() {
		return con;
	}

	public void setCon(String con) {
		this.con = con;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}
}
