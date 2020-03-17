/**
 * 这是用户信息类
 */
package com.qq.common;

import java.net.InetAddress;

public class User implements java.io.Serializable {

	private String userId;
	private String passwd;
	private String unload;
	private String registe;
	private InetAddress address;
	public InetAddress getAddress() {
		return address;
	}
	public void setAddress(InetAddress address) {
		this.address = address;
	}
	public String getRegiste() {
		return registe;
	}
	public void setRegiste(String registe) {
		this.registe = registe;
	}
	public String getUnload() {
		return unload;
	}
	public void setUnload(String unload) {
		this.unload = unload;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
