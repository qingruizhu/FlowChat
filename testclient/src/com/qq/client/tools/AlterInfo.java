package com.qq.client.tools;

import javax.swing.*;

import com.qq.common.Message;
import com.qq.common.MessageType;

import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;

public class AlterInfo extends JFrame implements ActionListener{
	private boolean isDragged = false;
	private Point loc = null;  

    private Point tmp = null;
  //顶部控件
  	JLabel p_qianming,p_name,p_image,p_info;
  	JButton p_edit,p_close,p_min,p_Close,p_Save;
  	private JLabel lblNewLabel;
  	private JPanel panel_1;
  	private JPanel panel_2;
  	
  	//中部控件
  	private JTextField a_nicheng,a_rname,a_birthday,a_phone,a_school,a_email,a_zhuye,a_guxiang,a_suozaidi,a_address,a_youbian,a_age;
  	private JComboBox a_sex,a_shengxiao,a_xingzuo,a_blood,a_caeer,a_xueli;
  	private JLabel label;
  	private JLabel label_1;
  	private JLabel label_2;
  	private JLabel label_3;
  	private JLabel label_4;
  	private JLabel label_5;
  	private JLabel label_6;
  	private JLabel label_7;
  	private JLabel label_8;
  	private JLabel label_9;
  	private JLabel label_10;
  	private JLabel label_11;
  	private JLabel label_12;
  	private JLabel label_13;
  	private JLabel label_14;
  	private JLabel label_15;
  	private JLabel label_16;
  	
  	PersonalInfo personalinfo;
  	String str;
  	String[] strs;
  	String owner;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AlterInfo ai = new AlterInfo("sdf");

	}
	public AlterInfo (String own){

		owner=own;
		this.setSize(458,679);
		
		
		this.setUndecorated(true);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 458, 252);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		p_image = new JLabel(new ImageIcon("image/Qtouxiang.jpg"));
		p_image.setBounds(10,92, 78, 78);
		panel.add(p_image);
		
		p_qianming = new JLabel("签名");
		p_qianming.setBounds(100, 156, 226, 15);
		panel.add(p_qianming);
		
		p_info = new JLabel(new ImageIcon("image/INFO.jpg"));
		p_info.setBounds(0, 184, 68,36);
		panel.add(p_info);
		p_Close = new JButton(new ImageIcon("image/Close.png"));
		p_Close.setBounds(365, 225, 62,21 );
		p_Close.addActionListener(this);
		panel.add(p_Close);
		p_Save = new JButton(new ImageIcon("image/save.png"));
		p_Save.setBounds(280, 225, 62,21 );
		p_Save.addActionListener(this);
		panel.add(p_Save);
		p_close = new JButton(new ImageIcon("image/exit.jpg"));
		p_close.setBounds(428, 0, 30, 30);
		p_close.addActionListener(this);
		panel.add(p_close);
		p_min = new JButton(new ImageIcon("image/min.png"));
		p_min.setBounds(398, 0, 30, 30);
		p_min.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		setExtendedState(JFrame.ICONIFIED);	
			}
		});
		panel.add(p_min);
		p_name = new JLabel(this.owner);
		p_name.setBounds(102, 93, 107, 30);
		panel.add(p_name);
		JLabel background = new JLabel(new ImageIcon("image/beijing.png"));
		background.setBounds(0, 0, 458, 214);
		panel.add(background);
		JLabel background1 = new JLabel(new ImageIcon("image/white.png"));
		background1.setBounds(0, 214, 458,38);
		panel.add(background1);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 603, 458, 76);
		getContentPane().add(panel_1);
		JLabel background2 = new JLabel(new ImageIcon("image/beijing.png"));
		background2.setBounds(0, 603, 458, 76);
		panel_1.add(background2);
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, 253, 458, 349);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		a_nicheng = new JTextField();
		a_nicheng.setBounds(88, 10, 103, 21);
		panel_2.add(a_nicheng);
		a_nicheng.setColumns(10);
		
		a_rname = new JTextField();
		a_rname.setBounds(298, 10, 103, 21);
		panel_2.add(a_rname);
		a_rname.setColumns(10);
		
		a_sex = new JComboBox();
		a_sex.setBounds(88, 37, 103, 21);
		a_sex.setModel(new DefaultComboBoxModel(new String[] {"男","女"}));
		panel_2.add(a_sex);
		a_age = new JTextField();
		a_age.setBounds(298, 37, 103, 21);
		panel_2.add(a_age);
		
		a_birthday = new JTextField();
		a_birthday.setBounds(88, 64, 103, 21);
		panel_2.add(a_birthday);
		a_birthday.setColumns(10);
		a_shengxiao = new JComboBox();
		a_shengxiao.setBounds(298, 64, 103, 21);
		a_shengxiao.setModel(new DefaultComboBoxModel(new String[] {"龙","虎","狗","猪","鼠","兔","牛","羊","蛇","猴","鸡","马"}));
		panel_2.add(a_shengxiao);
		a_xingzuo = new JComboBox();
		a_xingzuo.setBounds(88, 91, 103, 21);
		a_xingzuo.setModel(new DefaultComboBoxModel(new String[] {"白羊座","金牛座","双子座","巨蟹座","狮子座","处女座","天秤座","天蝎座","射手座","摩羯座","水瓶座","双鱼座"}));
		panel_2.add(a_xingzuo);
		a_blood = new JComboBox();
		a_blood.setBounds(298, 91, 103, 21);
		a_blood.setModel(new DefaultComboBoxModel(new String[] {"A型","B型","AB型","O型","RH型"}));
		panel_2.add(a_blood);
		a_caeer = new JComboBox();
		a_caeer.setBounds(88, 118, 103, 21);
		a_caeer.setModel(new DefaultComboBoxModel(new String[] {"在校学生","固定工作者","自由职业者","固定工作者","待业/无业/失业","其他"}));
		panel_2.add(a_caeer);
		a_xueli = new JComboBox();
		a_xueli.setBounds(88, 145, 103, 21);
		a_xueli.setModel(new DefaultComboBoxModel(new String[] {"小学及以下","小学","初中","高中","中专","大专","本科","研究生","博士及以上"}));
		panel_2.add(a_xueli);
		a_school = new JTextField();
		a_school.setBounds(298, 145, 103, 21);
		panel_2.add(a_school);
		a_school.setColumns(10);
		a_phone = new JTextField();
		a_phone.setBounds(88,172 , 135, 21);
		panel_2.add(a_phone);
        a_phone.setColumns(10);
        a_email = new JTextField();
		a_email.setBounds(88,199 , 313, 21);
		panel_2.add(a_email);
        a_email.setColumns(10);
        a_zhuye = new JTextField();
		a_zhuye.setBounds(88,226 , 313, 21);
		panel_2.add(a_zhuye);
        a_zhuye.setColumns(10);
        a_guxiang = new JTextField();
		a_guxiang.setBounds(88,253 , 313, 21);
		panel_2.add(a_guxiang);
        a_guxiang.setColumns(10);
        a_address = new JTextField();
		a_address.setBounds(88,280 , 313, 21);
		panel_2.add(a_address);
        a_address.setColumns(10);
        a_youbian = new JTextField();
        a_youbian.setBounds(88,307 , 313, 21);
		panel_2.add(a_youbian);
		a_youbian.setColumns(10);
		
		label = new JLabel("\u90AE\u7F16");
		label.setBounds(10, 310, 54, 15);
		panel_2.add(label);
		
		label_1 = new JLabel("\u5730\u5740");
		label_1.setBounds(10, 283, 54, 15);
		panel_2.add(label_1);
		
		label_2 = new JLabel("\u6545\u4E61");
		label_2.setBounds(10, 256, 54, 15);
		panel_2.add(label_2);
		
		label_3 = new JLabel("\u4E3B\u9875");
		label_3.setBounds(10, 229, 54, 15);
		panel_2.add(label_3);
		
		label_4 = new JLabel("\u90AE\u7BB1");
		label_4.setBounds(10, 202, 54, 15);
		panel_2.add(label_4);
		
		label_5 = new JLabel("\u624B\u673A");
		label_5.setBounds(10, 175, 54, 15);
		panel_2.add(label_5);
		
		label_6 = new JLabel("\u5B66\u5386");
		label_6.setBounds(10, 148, 54, 15);
		panel_2.add(label_6);
		
		label_7 = new JLabel("\u804C\u4E1A");
		label_7.setBounds(10, 121, 54, 15);
		panel_2.add(label_7);
		
		label_8 = new JLabel("\u661F\u5EA7");
		label_8.setBounds(10, 94, 54, 15);
		panel_2.add(label_8);
		
		label_9 = new JLabel("\u751F\u65E5");
		label_9.setBounds(10, 67, 54, 15);
		panel_2.add(label_9);
		
		label_10 = new JLabel("\u6027\u522B");
		label_10.setBounds(10, 40, 54, 15);
		panel_2.add(label_10);
		
		label_11 = new JLabel("\u6635\u79F0");
		label_11.setBounds(10, 13, 54, 15);
		panel_2.add(label_11);
		
		label_12 = new JLabel("\u59D3\u540D");
		label_12.setBounds(234, 13, 54, 15);
		panel_2.add(label_12);
		
		label_13 = new JLabel("\u5E74\u9F84");
		label_13.setBounds(234, 40, 54, 15);
		panel_2.add(label_13);
		
		label_14 = new JLabel("\u751F\u8096");
		label_14.setBounds(234, 67, 54, 15);
		panel_2.add(label_14);
		
		label_15 = new JLabel("\u8840\u578B");
		label_15.setBounds(234, 94, 54, 15);
		panel_2.add(label_15);
		
		label_16 = new JLabel("\u5B66\u6821");
		label_16.setBounds(234, 148, 54, 15);
		panel_2.add(label_16);
        
        
		
		
		
		
		
		this.setVisible(true);
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 510) / 2;  

        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 290) / 2;  
        // 为窗体添加鼠标事件  

        this.addMouseListener(new java.awt.event.MouseAdapter() {  

            public void mouseReleased(MouseEvent e) {  

                isDragged = false;  

                // 为指定的光标设置光标图像  

                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));  

            }  

   

            public void mousePressed(MouseEvent e) {  

            	if(e.getY()<30)
            	{
                tmp = new Point(e.getX(), e.getY());  

                isDragged = true;  

                setCursor(new Cursor(Cursor.MOVE_CURSOR));  

            }  
            }

        });  

   

        this.addMouseMotionListener(new MouseMotionAdapter() {  

            // 鼠标按键在组件上按下并拖动时调用。  

            public void mouseDragged(MouseEvent e) {  
            	
                if (isDragged) {  

                    loc = new Point(getLocation().x + e.getX() - tmp.x,  

                            getLocation().y + e.getY() - tmp.y);  

                    setLocation(loc);  

                }  

            }  

        });
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getSource()==p_close)
		{
			this.dispose();
		}
		if(arg0.getSource()==p_Close)
		{
			this.dispose();
			str=getTexts();
			personalinfo.setTexts(str);
			personalinfo.setVisible(true);
		}
		if(arg0.getSource()==p_Save)
		{
			str=getTexts();
			Message m=new Message();
			m.setMesType(MessageType.message_savedata);
			m.setSender(owner);
			m.setCon(str);
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(owner).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
 		}			
		}
	}
	public void getPersonalInfo(PersonalInfo personalinfo1)
	{
		this.personalinfo=personalinfo1;
		personalinfo.setVisible(false);
	}
	public void setTexts(String str)
	{
		this.str=str;
		strs=str.split(";");
		a_nicheng.setText(strs[1]);		
		a_rname.setText(strs[1]);				
		a_sex.setSelectedItem(strs[2]);
		a_age.setText(strs[3]);
		a_birthday.setText(strs[4]);		
		a_shengxiao.setSelectedItem(strs[5]);		
		a_xingzuo.setSelectedItem(strs[6]);
		a_blood.setSelectedItem(strs[7]);		
		a_caeer.setSelectedItem(strs[8]);		
		a_xueli.setSelectedItem(strs[9]);		
		a_school.setText(strs[10]);				
		a_phone.setText(strs[11]);		
        a_email.setText(strs[12]);		
        a_zhuye.setText(strs[13]);		
        a_guxiang.setText(strs[14]);		
        a_address.setText(strs[15]);		
        a_youbian.setText(strs[16]);      
	}
	public String getTexts()
	{
		String str="";
		str+=(a_nicheng.getText()+";"		
		+a_rname.getText()+";"			
		+a_sex.getSelectedItem()+";"	
		+a_age.getText()+";"	
		+a_birthday.getText()+";"			
		+a_shengxiao.getSelectedItem()+";"			
		+a_xingzuo.getSelectedItem()+";"	
		+a_blood.getSelectedItem()+";"		
		+a_caeer.getSelectedItem()+";"			
		+a_xueli.getSelectedItem()+";"		
		+a_school.getText()+";"					
		+a_phone.getText()+";"		
        +a_email.getText()+";"		
        +a_zhuye.getText()+";"			
        +a_guxiang.getText()+";"			
        +a_address.getText()+";"			
        +a_youbian.getText()+";");
		return str;
	}
	
}

