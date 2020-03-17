package com.qq.server.model;
import java.awt.List;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class LinkClass {

    List list=new List();int i=0;
    Connection con = null;  
	PreparedStatement pstm =null; 							
	ResultSet rs =null;
	String s=null;
	String s1;
	public static void main(String[] args) {
		// TODO 自动生成的方法存根	
	}
	public LinkClass()
	{
		
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433;databaseName=Chat;integratedSecurity=true"; 
		try {
			Class.forName(driver);
			
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Chat;integratedSecurity=true");
		}
			catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public ResultSet query(String sql){ 
		//查询返回结果 
		try{    
			pstm=con.prepareStatement(sql);
			rs=pstm.executeQuery();
			}    
		catch(Exception e){   
			System.out.println(e.getMessage());   
			}
		return rs;  
		
	}
	public void Insert(String sql)
	{
		try{    
			pstm=con.prepareStatement(sql);
			pstm.executeUpdate();
			}    
		catch(Exception e){   
			System.out.println(e.getMessage());   
			}
	}
	public List getAddress()
	{
		return list;
	}
	
	public ResultSet doSelect(String sql)
	{
		try {
		pstm=con.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		rs=pstm.executeQuery();
		return rs;
	} catch (SQLException e) {
		// TODO 自动生成的 catch 块
		e.printStackTrace();
	}finally{
		
	}
		
	return rs;
	}

	
		
	

}
