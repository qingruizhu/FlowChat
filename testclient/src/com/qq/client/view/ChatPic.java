package com.qq.client.view;
import javax.swing.*;
import java.net.URL;
public class ChatPic extends ImageIcon{
	/**
	 *ͼƬ���� 
	 */
	private static final long serialVersionUID = 1L;
	int im;//ͼƬ����
	
	public int getIm() {
		return im;
	}
	public void setIm(int im) {
		this.im = im;
	}
	public ChatPic(URL url,int im){
		super(url);
		this.im = im;
	}
}
