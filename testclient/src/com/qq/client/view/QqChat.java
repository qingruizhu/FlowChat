/**
 * 这是与好友聊天的界面
 * 因为客户端，要处于读取的状态，因此我们把它做成一个线程
 */
package com.qq.client.view;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.client.tools.ManageQqChat;
import com.qq.common.Message;
import com.qq.common.MessageType;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class QqChat extends JFrame implements ActionListener,WindowListener,MouseListener {

	private JButton cd_send,cd_close,cd_sendpic,cd_jietu,cd_yymessage,cd_diange,cd_dgninput,cd_zhendong;
	private JButton cd_biqoing,cd_word,cd_magic,cd_yuyin,cd_shipin,cd_transfile,cd_createteam,cd_yuancheng,cd_biaoqing;
	private JButton cd_sharedisplay,cd_yinyong;
	private JLabel cd_touxiang,cd_qianming,cd_nname;
	private JButton cd_exit,cd_min,cd_max,cd_searchjilu,cd_jiacu,cd_xiahuaxian,cd_color,cd_xieti;
	private JComboBox cd_ziti,cd_size;
	private boolean isDragged = false;
	private Point loc = null;  
	JPanel panel_1,panel_2,panel_3,panel_4;
    private Point tmp = null;
    CardLayout  Cardjilu;
    JTextPane textPane_1,textPane;
    JTextPane textPane_2 ;
    String ownerId;
	String friendId;
	private PicsJWindow picWindow;
	private List<PicInfo> myPicInfo = new LinkedList<PicInfo>();
	private List<PicInfo> receivdPicInfo = new LinkedList<PicInfo>();
	private List<PicInfo> receivdoldPicInfo = new LinkedList<PicInfo>();
	private StyledDocument docChat = null;
	private StyledDocument docMsg = null;
	private StyledDocument oldMsg = null;
	DataInputStream fis = null;
	////////////////////////////////////////////
	JTextPane k=new JTextPane();
	///////////////////////////////////////////
	Color Text_color=new Color(255,255,255);
	private JScrollPane scrollPane_1,scrollPane_2;
    /**
     * @wbp.nonvisual location=597,227
     */
    private final JLabel label2 = new JLabel("New label2222222222");
    /**
     * @wbp.nonvisual location=597,327
     */
    private final JLabel label1 = new JLabel(new ImageIcon("hh.gif"));
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//ChatDialog cd = new ChatDialog();
	
		QqChat a = new QqChat("sd","d");
	}
	class PicInfo{
		/* 图片信息*/
		int pos;
		String val;
		public PicInfo(int pos,String val){
			this.pos = pos;
			this.val = val;
		}
		public int getPos() {
			return pos;
		}
		public void setPos(int pos) {
			this.pos = pos;
		}
		public String getVal() {
			return val;
		}
		public void setVal(String val) {
			this.val = val;
		}
		
	}
	
	
	/**
	 * 插入图片
	 * 
	 * @param icon
	 */
	public void insertSendPic(ImageIcon imgIc) {
		//jpMsg.setCaretPosition(docChat.getLength()); // 设置插入位置
		textPane_1.insertIcon(imgIc); // 插入图片
	//	System.out.print(imgIc.toString());
		//insert(new FontAttrib()); // 这样做可以换行
	}
	
	public JButton getPicBtn(){
		return cd_biaoqing;
	}
	
	String Friendname;
	public QqChat (String owner,String friend) {
		
		
		this.addWindowListener(this);
		Text_color = Color.BLACK;
		this.ownerId=owner;
		this.friendId=friend;
		
//		QqFriendList mylist =  ManageQqFriendList.getQqFriendList(ownerId);
//		Enumeration<?> enumeration;
//		enumeration = mylist.root.preorderEnumeration();
//		while (enumeration.hasMoreElements()) { // 遍历枚举对象.

//			// 先定义一个节点变量.
//
//			DefaultMutableTreeNode node;
//
//			node = (DefaultMutableTreeNode) enumeration.nextElement();// 将节点名称给node.
//
//			String str = node.toString();
//			String friendname = str;
//	        if(str.length()>5)
//	        {
//	        	int x = str.indexOf("(");
//	        	str = str.substring(x+1, str.length()-1);
//	        	if(str.equals(friendId))
//	        		this.Friendname = friendname;
//	        }
//
//
//		}
//
		
		this.setSize(728,533);
		
		this.setUndecorated(true);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 729, 92);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		cd_touxiang = new JLabel(new ImageIcon("image/dialogimage/huisetouxiang.png"));
		cd_touxiang.setBounds(10, 10, 42, 42);
		cd_touxiang.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent arg0) {

				// TODO 自动生成的方法存根
				if (arg0.getSource() == cd_touxiang) {
					Message m = new Message();
					m.setMesType(MessageType.message_fridata);
					m.setSender(friendId);
					m.setGetter(ownerId);
					try {
						ObjectOutputStream oos = new ObjectOutputStream(
								ManageClientConServerThread
										.getClientConServerThread(ownerId).getS()
										.getOutputStream());
						oos.writeObject(m);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			public void mousePressed(MouseEvent e) {
				// TODO 自动生成的方法存根

			}

					public void mouseReleased(MouseEvent e) {
						// TODO 自动生成的方法存根
						
					}

					public void mouseEntered(MouseEvent e) {
						// TODO 自动生成的方法存根
						
					}

					public void mouseExited(MouseEvent e) {
						// TODO 自动生成的方法存根
						
					}
		});
		panel.add(cd_touxiang);
		
		cd_exit = new JButton(new ImageIcon ("image/dialogimage/dexit.jpg"));
		cd_exit.setAlignmentX(Component.CENTER_ALIGNMENT);
		cd_exit.addActionListener(this);
		
		JLabel friendname = new JLabel(this.Friendname);
		friendname.setBounds(62, 10, 105, 20);
		panel.add(friendname);
		cd_exit.setBounds(698, 0, 30, 30);
		panel.add(cd_exit);
		
		cd_min = new JButton(new ImageIcon ("image/dialogimage/dmin.jpg"));
		cd_min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		cd_min.setBounds(640, 0, 30, 30);
		panel.add(cd_min);
		
		cd_max = new JButton(new ImageIcon ("image/dialogimage/dmax.jpg"));
		cd_max.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.MAXIMIZED_BOTH);  

			}
		});
		
		cd_max.setBounds(669, 0, 30,30);
		panel.add(cd_max);
		
		cd_yuyin = new JButton(new ImageIcon("image/dialogimage/yuyin.png"));
		cd_yuyin.setBounds(10, 62, 36, 30);
		panel.add(cd_yuyin);
		
		cd_shipin = new JButton(new ImageIcon("image/dialogimage/shipin.png"));
		cd_shipin.setBounds(56, 62, 36, 30);
		cd_shipin.addActionListener(this);
		panel.add(cd_shipin);
		
		cd_transfile = new JButton(new ImageIcon("image/dialogimage/tranfile.jpg"));
		cd_transfile.setBounds(102, 62, 36, 30);
		cd_transfile.addActionListener(this);
		panel.add(cd_transfile);
		
		cd_createteam = new JButton(new ImageIcon("image/dialogimage/createteam.jpg"));
		cd_createteam.setBounds(148, 62, 36, 30);
		panel.add(cd_createteam);
		
		cd_yuancheng = new JButton(new ImageIcon("image/dialogimage/yuancheng.png"));
		cd_yuancheng.setBounds(194, 62, 36, 30);
		panel.add(cd_yuancheng);
		
		cd_sharedisplay = new JButton(new ImageIcon("image/dialogimage/sharedisplay.png"));
		cd_sharedisplay.setBounds(240, 62, 36, 30);
		panel.add(cd_sharedisplay);
		
		JButton cd_yingyong = new JButton(new ImageIcon("image/dialogimage/yingyong.jpg"));
		cd_yingyong.setBounds(286, 62, 36, 30);
		panel.add(cd_yingyong);
		
		panel_1 = new JPanel();
		panel_1.setBounds(0, 91, 446, 259);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		
		textPane = new JTextPane();
		textPane.setBounds(0, 0, 446, 259);
		
		
		scrollPane_1 = new JScrollPane(textPane);
		scrollPane_1.setBounds(0, 0, 446, 259);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		panel_1.add(scrollPane_1);
		
		panel_2 = new JPanel();
		panel_2.setBounds(0, 385, 446, 148);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		textPane_1 = new JTextPane();
		textPane_1.setBounds(0, 44, 446, 53);
		panel_2.add(textPane_1);
		
		docMsg = textPane_1.getStyledDocument();
		docChat = textPane.getStyledDocument();
		
		cd_searchjilu = new JButton(new ImageIcon("image/dialogimage/jilu.jpg"));
		Cardjilu = new CardLayout(2, 2);
		cd_searchjilu.addActionListener(this);
		cd_searchjilu.setBounds(353, 11, 93, 23);
		panel_2.add(cd_searchjilu);
		
		picWindow = new PicsJWindow(this);
		
		panel_4 = new JPanel();
		panel_4.setVisible(false);
		
		panel_4.setBounds(0, 350, 446, 36);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);
		
		cd_size = new JComboBox();
		cd_size.setModel(new DefaultComboBoxModel(new String[] {"16", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20"}));
		cd_size.setBounds(280, 9, 40, 21);
		cd_size.addActionListener(this);
		panel_4.add(cd_size);
		
		cd_jiacu = new JButton(new ImageIcon("image/dialogimage/jiacu.jpg"));
		cd_jiacu.setBounds(330, 10, 22, 20);
		panel_4.add(cd_jiacu);
		
		cd_xieti = new JButton(new ImageIcon("image/dialogimage/xieti.jpg"));
		cd_xieti.setBounds(362, 10, 22, 20);
		panel_4.add(cd_xieti);
		
		cd_xiahuaxian = new JButton(new ImageIcon("image/dialogimage/xiahuaxian.jpg"));
		cd_xiahuaxian.setBounds(394, 10, 22, 20);
		panel_4.add(cd_xiahuaxian);
		
		cd_color = new JButton(new ImageIcon("image/dialogimage/color.jpg"));
		cd_color.setBounds(426, 10, 20, 20);
		cd_color.addActionListener(this );
		panel_4.add(cd_color);
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String script[] = ge.getAvailableFontFamilyNames();
		cd_ziti = new JComboBox();
		cd_ziti.setModel(new DefaultComboBoxModel(script));
		cd_ziti.addActionListener(this);
		cd_ziti.setBounds(182, 9, 88, 21);
		panel_4.add(cd_ziti);
		 
		cd_word = new JButton(new ImageIcon("image/dialogimage/word.png"));
		cd_word.addActionListener(this);
				
		cd_word.setBounds(10, 11, 30, 30);
		panel_2.add(cd_word);
		
		cd_biaoqing = new JButton(new ImageIcon("image/dialogimage/biaoqing.png"));
		cd_biaoqing.setBounds(39, 11, 30, 30);
		cd_biaoqing.addMouseListener(this);
		panel_2.add(cd_biaoqing);
		
		cd_magic = new JButton(new ImageIcon("image/dialogimage/magic.jpg"));
		cd_magic.setBounds(68, 11, 30, 30);
		panel_2.add(cd_magic);
		
		cd_zhendong = new JButton(new ImageIcon("image/dialogimage/zhendong.jpg"));
		cd_zhendong.setBounds(97, 11, 30, 30);
		cd_zhendong.addActionListener(this);
		panel_2.add(cd_zhendong);
		
		cd_yymessage = new JButton(new ImageIcon("image/dialogimage/yymessage.jpg"));
		cd_yymessage.setBounds(126, 11, 30, 30);
		panel_2.add(cd_yymessage);
		
		cd_dgninput = new JButton(new ImageIcon("image/dialogimage/dgninput.jpg"));
		cd_dgninput.setBounds(155, 11,30, 30);
		panel_2.add(cd_dgninput);
		
		cd_sendpic = new JButton(new ImageIcon("image/dialogimage/sendimage.jpg"));
		cd_sendpic.setBounds(184, 11, 30, 30);
		panel_2.add(cd_sendpic);
		
		cd_diange = new JButton(new ImageIcon("image/dialogimage/diange.jpg"));
		cd_diange.setBounds(214, 11, 30, 30);
		panel_2.add(cd_diange);
		
		cd_jietu = new JButton(new ImageIcon("image/dialogimage/jietu.jpg"));
		cd_jietu.setBounds(244, 11, 30, 30);
		cd_jietu.addActionListener(this);
		panel_2.add(cd_jietu);
		
		cd_close = new JButton(new ImageIcon("image/dialogimage/close.jpg"));
		cd_close.setBounds(307, 114, 64, 24);
		panel_2.add(cd_close);
		
		cd_send = new JButton(new ImageIcon("image/dialogimage/send.jpg"));
		cd_send.addActionListener(this);
		cd_send.setBounds(381, 114, 64, 24);
		panel_2.add(cd_send);
		
		panel_3 = new JPanel();
		panel_3.setBounds(444, 91, 285, 422);
		getContentPane().add(panel_3);
		

		panel_3.setLayout(Cardjilu);
		
		textPane_2 = new JTextPane();
		
		scrollPane_2 = new JScrollPane(textPane_2);
		scrollPane_2.setBounds(2, 2, 411, 418);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		panel_3.add(scrollPane_2, "name_3333");
		
		panel_3.add(label1,"1");
		panel_3.add(scrollPane_2,"2");
		textPane_2.setBounds(2, 2, 411, 418);
		oldMsg = textPane_2.getStyledDocument();
		
		this.addComponentListener(new ComponentAdapter(){

			@Override
			public void componentResized(ComponentEvent e) {
				QqChat.this.picWindow.dispose();
			}

			@Override
			public void componentMoved(ComponentEvent e) {
				QqChat.this.picWindow.dispose();
			}

			@Override
			public void componentHidden(ComponentEvent e) {
				QqChat.this.picWindow.dispose();
			}
			
		});
		
		cd_searchjilu.addActionListener(this);
			

		
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
        
        drag();///////////////拖放功能
	}
	
	
	
	public Color recolor(String s)
	{
		String friend[]=s.split(",");
		String friend1[]= new String[friend.length];
		int j;
		for(int i =0; i < friend.length; i++)
		{
			j = friend[i].lastIndexOf("=");
			friend1[i] = friend[i].substring(j+1);
		}
		friend1[2] = friend1[2].substring(0, friend1[2].length()-1);
		return new Color(Integer.parseInt(friend1[0]),Integer.parseInt(friend1[1]),Integer.parseInt(friend1[2]));
	}
	
	/*
	 * 重组收到的表情信息串
	 */
	public void receivedPicInfo(String picInfos){
		String[] infos = picInfos.split("[+]");
		for(int i = 0 ; i < infos.length ; i++){
			String[] tem = infos[i].split("[&]");
			if(tem.length==2){
				PicInfo pic = new PicInfo(Integer.parseInt(tem[0]),tem[1]);
				receivdPicInfo.add(pic);
			}
		}
	}
	
	public void receivedoldPicInfo(String picInfos){
		String[] infos = picInfos.split("[+]");
		for(int i = 0 ; i < infos.length ; i++){
			String[] tem = infos[i].split("[&]");
			if(tem.length==2){
				PicInfo pic = new PicInfo(Integer.parseInt(tem[0]),tem[1]);
				receivdoldPicInfo.add(pic);
			}
		}
	}
	
	//写一个方法，让它显示消息
	public void showMessage(Message m)
	{
		String info=Friendname+" "+m.getSendTime()+" :\n";
		SimpleAttributeSet attrset = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrset,14);
        StyleConstants.setForeground(attrset, Color.green);
        StyleConstants.setFontFamily(attrset, "仿宋");
        Document docs = textPane.getDocument();
        try {
            docs.insertString(docs.getLength(), info, attrset);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        
        info=m.getCon();
        int index = info.lastIndexOf("*");
        StyleConstants.setFontSize(attrset,m.getSize());
        StyleConstants.setForeground(attrset, recolor(m.getCol()));
        StyleConstants.setFontFamily(attrset, m.getFont());
        docs = textPane.getDocument();
        pos1 = docs.getLength();
        if(index>0 && index < info.length()-1){
			try {
	            docs.insertString(docs.getLength(), info.substring(0,index)+"\r\n", attrset);
	     //       jta.setCaretPosition(docChat.getLength()); // 设置滚动到最下边
	        } catch (BadLocationException e) {
	            e.printStackTrace();
	        }
			receivedPicInfo(info.substring(index+1,info.length()));
			insertPics(true);
		}else{
			
			try {
	            docs.insertString(docs.getLength(), info.substring(0,index)+"\r\n", attrset);
	      //      jta.setCaretPosition(docChat.getLength()); // 设置滚动到最下边
	        } catch (BadLocationException e) {
	            e.printStackTrace();
	        }
		}

	}
	
	//写一个方法，让它显示消息
		public void showoldMessage(Message m)
		{
			
			String info=m.getFriname()+" "+m.getSendTime()+" :\n";
			SimpleAttributeSet attrset = new SimpleAttributeSet();
	        StyleConstants.setFontSize(attrset,14);
	        StyleConstants.setForeground(attrset, Color.green);
	        StyleConstants.setFontFamily(attrset, "仿宋");
	        Document docs = textPane_2.getDocument();
	        try {
	            docs.insertString(docs.getLength(), info, attrset);
	        } catch (BadLocationException e) {
	            e.printStackTrace();
	        }
	        
	        info=m.getCon();
	        int index = info.lastIndexOf("*");
	        StyleConstants.setFontSize(attrset,m.getSize());
	        StyleConstants.setForeground(attrset, recolor(m.getCol()));
	        StyleConstants.setFontFamily(attrset, m.getFont());
	        docs = textPane_2.getDocument();
	        pos3 = docs.getLength();
	        if(index>0 && index < info.length()-1){
				try {
				//	JOptionPane.showMessageDialog(this,m.getCon());
		            docs.insertString(docs.getLength(), info.substring(0,index)+"\r\n", attrset);
		     //       jta.setCaretPosition(docChat.getLength()); // 设置滚动到最下边
		        } catch (BadLocationException e) {
		            e.printStackTrace();
		        }
				receivedoldPicInfo(info.substring(index+1,info.length()));
				insertoldPics();
			}else{
				
				try {
					if(info.length()>0)
						docs.insertString(docs.getLength(), info.substring(0,index)+"\r\n", attrset);
		      //      jta.setCaretPosition(docChat.getLength()); // 设置滚动到最下边
		        } catch (BadLocationException e) {
		            e.printStackTrace();
		        }
			}

		}
	
	public void showownMessage(Message m)
	{
		String info="我 "+" "+m.getSendTime()+" :\n";
		SimpleAttributeSet attrset = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrset,14);
        StyleConstants.setForeground(attrset, Color.green);
        StyleConstants.setFontFamily(attrset, "仿宋");
        Document docs = textPane.getDocument();
        try {
            docs.insertString(docs.getLength(), info, attrset);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        
        
        info=m.getCon()+"\r\n";
        StyleConstants.setFontSize(attrset,m.getSize());
        StyleConstants.setForeground(attrset, recolor(m.getCol()));
        StyleConstants.setFontFamily(attrset, m.getFont());
        docs = textPane.getDocument();
        pos2 = docs.getLength();
        try {
            docs.insertString(docs.getLength(), info, attrset);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        insertPics(false);
	}
	
	public void shake(Message m){
		showownMessage(m);
		setExtendedState(Frame.NORMAL);
		setVisible(true);
		new Thread(){
			long begin = System.currentTimeMillis();
			long end = System.currentTimeMillis();
			Point p = QqChat.this.getLocationOnScreen();
			public void run(){
				int i = 1;
				while((end-begin)/1000<2){
					QqChat.this.setLocation(new Point((int)p.getX()-5*i,(int)p.getY()+5*i));
					end = System.currentTimeMillis();
					try {
						Thread.sleep(5);
						i=-i;
						QqChat.this.setLocation(p);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	/**
	 * 插入图片
	 * 
	 * @param isFriend 是否为朋友发过来的消息
	 */
	int pos1;
	int pos2;
	private JButton sendfile;
	private JButton shake;
	private void insertPics(boolean isFriend) {
		if(isFriend){
			if(this.receivdPicInfo.size()<=0){
				return;
			}else{
				for(int i = 0 ; i < receivdPicInfo.size() ; i++){
					PicInfo pic = receivdPicInfo.get(i);
					String fileName;
					textPane.setCaretPosition(pos1+pic.getPos()); /*设置插入位置*/
		            fileName= "face/"+pic.getVal()+".gif";/*修改图片路径*/ 
		            textPane.insertIcon(new  ImageIcon(PicsJWindow.class.getResource(fileName))); /*插入图片*/
/*					jpChat.updateUI();*/
				}
				receivdPicInfo.clear();
			}
		}else{
			
			if(myPicInfo.size()<=0){
				return;
			}else{
				for(int i = 0 ; i < myPicInfo.size() ; i++){
					PicInfo pic = myPicInfo.get(i);
					
					textPane.setCaretPosition(pos2+pic.getPos()); /*设置插入位置*/
					String fileName;
		            fileName= "face/"+pic.getVal()+".gif";/*修改图片路径*/ 
		            textPane.insertIcon(new  ImageIcon(PicsJWindow.class.getResource(fileName))); /*插入图片*/
					/*jpChat.updateUI();*/
				}
				myPicInfo.clear();
			}
		}
		textPane.setCaretPosition(docChat.getLength()); /*设置滚动到最下边*/
		//insert(new FontAttrib()); /*这样做可以换行*/
	}
	
	int pos3;
	private void insertoldPics() {
		
			if(this.receivdoldPicInfo.size()<=0){
				return;
			}else{
				for(int i = 0 ; i < receivdoldPicInfo.size() ; i++){
					PicInfo pic = receivdoldPicInfo.get(i);
					String fileName;
					textPane_2.setCaretPosition(pos3+pic.getPos()); /*设置插入位置*/
		            fileName= "face/"+pic.getVal()+".gif";/*修改图片路径*/ 
		            textPane_2.insertIcon(new  ImageIcon(PicsJWindow.class.getResource(fileName))); /*插入图片*/
/*					jpChat.updateUI();*/
				}
				receivdoldPicInfo.clear();
			}
			textPane_2.setCaretPosition(oldMsg.getLength()); /*设置滚动到最下边*/
	}
	
	/**
	 * 重组发送的表情信息
	 * @return 重组后的信息串  格式为   位置|代号+位置|代号+……
	 */
	private String buildPicInfo(){
		StringBuilder sb = new StringBuilder("");
		//遍历jtextpane找出所有的图片信息封装成指定格式
		  for(int i = 0; i < this.textPane_1.getText().length(); i++){ 
              if(docMsg.getCharacterElement(i).getName().equals("icon")){
            	  
            	  //ChatPic = (ChatPic)
            	  Icon icon = StyleConstants.getIcon(textPane_1.getStyledDocument().getCharacterElement(i).getAttributes());
            	  ChatPic cupic = (ChatPic)icon;
            	  PicInfo picInfo= new PicInfo(i,cupic.getIm()+"");
            	  myPicInfo.add(picInfo);
            	  sb.append(i+"&"+cupic.getIm()+"+");
             } 
          }
		  System.out.println(sb.toString());
		  return sb.toString();
		  //return null;
	}
	
	
	
	public void mouseClicked(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		picWindow.setVisible(false);
	}
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		if (getY() <= 0) {
			setLocation(getX(), 0);
		}
		if (e.getButton() != 1)
			return;/*不是左键*/

		JComponent source = (JComponent) e.getSource();
		/*鼠标释放时在事件源内,才响应单击事件*/
		if (e.getX() >= 0 && e.getX() <= source.getWidth() && e.getY() >= 0
				&& e.getY() <= source.getHeight()) {
			if (source == this.cd_biaoqing){
				picWindow.setVisible(true);
			}
		}
	}
	public void mouseEntered(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void mouseExited(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void windowOpened(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void windowClosing(WindowEvent e) {
		// TODO 自动生成的方法存根
		ManageQqChat.RemoveQqChat(this.ownerId+" "+this.friendId);
	}
	public void windowClosed(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void windowIconified(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void windowDeiconified(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void windowActivated(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void windowDeactivated(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}
	boolean c = true;
	int count=1;
	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		
		
		if(arg0.getSource()==cd_shipin)
		{
			Message m=new Message();
			m.setMesType(MessageType.message_ret_ip);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setCon("");
			m.setMyport(999);
			m.setFriendport(888);
			m.setSendTime(new java.util.Date().toString());
			
			//发送给服务器.
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}	
		}
		
		if(arg0.getSource()==cd_word)
		{
			if(c){
				panel_4.setVisible(true);
				c = false;
				}else{
					panel_4.setVisible(false);
					c = true;					
				}
		}
		
		if(arg0.getSource()==cd_searchjilu)
		{
			//this.setLayout(null);
			//this.setSize(728,533);
			//k.setBounds(446, 91, 283, 422);
			//this.add(k);
			
			if(count==1)
			{
				Cardjilu.show(panel_3, "2");
			//	textPane_2
				count+=1;
			//	JOptionPane.showMessageDialog(this,"用户名密码错误");
				
				Message m=new Message();
				m.setMesType(MessageType.message_ret_oldMessage);
				m.setSender(this.ownerId);
				m.setGetter(this.friendId);
				//m.setCon("old");///old是查询标志	
				m.setSendTime(new java.util.Date().toString());
				
				//发送给服务器.
				try {
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
					oos.writeObject(m);
					
				} catch (Exception e) {
					e.printStackTrace();
					// TODO: handle exception
				}			
			}
			else if(count == 2)
			{
				count+=1;
			}
			else if(count == 3)
			{
				count+=1;
			}
			else if(count==4)
			{
				Cardjilu.show(panel_3, "1");
				count=1;
				textPane_2.setText("");
			}
		}
		if(arg0.getSource()==cd_transfile)
		{
			JFileChooser fileDialog=new JFileChooser();
			fileDialog.showOpenDialog(this);
			File file=fileDialog.getSelectedFile();
			String type=file.getName().substring(file.getName().indexOf(".")+1);
			
			//JOptionPane.showMessageDialog(this,type);
			
			Message m=new Message();
			m.setMesType(MessageType.message_sendfile);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setFile(type);
			
			try {
				fis=new DataInputStream(new FileInputStream(file));
				int size=fis.available();
				byte[] by=new byte[size];
				fis.read(by);
				fis.close();
				m.setFilebyte(by, size);
				String ss="";
				String s=null;
				
			    m.setSendTime(new java.util.Date().toString());
			    ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);				 				      
			    } catch (Exception e) {
				    e.printStackTrace();
				   // TODO: handle exception			    
			    }
		}
		
		if(arg0.getSource()==cd_zhendong)
		{
			Message m=new Message();
			m.setMesType(MessageType.message_shake);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			
			m.setCol(Color.red.toString());
			m.setFont("黑体");
			m.setSize(14);
			
			DateFormat ddtf = DateFormat.getDateTimeInstance();  
			m.setSendTime(ddtf.format(new Date()));
			m.setCon(this.ownerId+" 发送了一个窗口抖动！");
			this.showownMessage(m);
			//发送给服务器.
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}		
		}
		
		
		if(arg0.getSource()==cd_send)
		{
			//如果用户点击了，发送按钮
			String pic = buildPicInfo();
			Message m=new Message();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
		//	JOptionPane.showMessageDialog(this,jtf.getText()+"*"+buildPicInfo());
			m.setCon(textPane_1.getText());
			m.setCol(this.Text_color.toString());
			m.setFont(cd_ziti.getSelectedItem().toString());
			m.setSize(Integer.parseInt(cd_size.getSelectedItem().toString()));
			DateFormat ddtf = DateFormat.getDateTimeInstance();  
			m.setSendTime(ddtf.format(new Date()));
			this.showownMessage(m);
			
			m.setCon(textPane_1.getText()+"*"+pic);
		//	this.showMessage(m);
			this.textPane_1.setText("");
			//发送给服务器.
			try {
				ObjectOutputStream oos=new ObjectOutputStream
				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			
		}
		if(arg0.getSource()==cd_ziti ||arg0.getSource()==cd_size)
		{
			textPane_1.setFont(new Font(cd_ziti.getSelectedItem().toString(), Font.PLAIN, Integer.parseInt(cd_size.getSelectedItem().toString())));
		//	
		}
		if(arg0.getSource()==cd_color)
		{
			Text_color=JColorChooser.showDialog(null,"请选择你喜欢的颜色" ,Text_color);  
			textPane_1.setForeground(Text_color);
//			if (Text_color==null )
//				Text_color=Color.lightGray; 
		//	jtf.setFont(new Font(ziti.getSelectedItem().toString(), Font.PLAIN, Integer.parseInt(size.getSelectedItem().toString())));
	  
		}   
		if(arg0.getSource()==cd_exit)
		{
			ManageQqChat.RemoveQqChat(this.ownerId+" "+this.friendId);
			this.dispose();	
		}
		if(arg0.getSource()==cd_jietu)
		{
			 //ScreenCapture  k = new ScreenCapture();
			ScreenCapture.screenShot();
			 //k.dialog.setVisible(false);
			 //k.screenShot();
			//this.add(textPane_1);
		}
		
	}
	///////////////////////////拖放功能
	public void drag()//定义的拖拽方法
    {
        //panel表示要接受拖拽的控件
        new DropTarget(textPane_1, DnDConstants.ACTION_COPY_OR_MOVE, new DropTargetAdapter()
        {

            public void drop(DropTargetDropEvent dtde)//重写适配器的drop方法
            {
                try
                {
                    if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor))//如果拖入的文件格式受支持
                    {
                        dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);//接收拖拽来的数据
                        List<File> list =  (List<File>) (dtde.getTransferable().getTransferData(DataFlavor.javaFileListFlavor));
                        String filepath="";
                        for(File file:list)
                        filepath+=file.getAbsolutePath();                                
                        dtde.dropComplete(true);//指示拖拽操作已完成
                        
            			String type=filepath.substring(filepath.indexOf(".")+1);
            			
            			
            			//JOptionPane.showMessageDialog(this,type);
            			
            			Message m=new Message();
            			m.setMesType(MessageType.message_sendfile);
            			m.setSender(ownerId);
            			m.setGetter(friendId);
            			m.setFile(type);
            			File  file=new File(filepath);
            			try {
            				fis=new DataInputStream(new FileInputStream(file));
            				int size=fis.available();
            				byte[] by=new byte[size];
            				fis.read(by);
            				fis.close();
            				m.setFilebyte(by, size);
            				String ss="";
            				String s=null;
            				
            			    m.setSendTime(new java.util.Date().toString());
            			    ObjectOutputStream oos=new ObjectOutputStream
            				(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
            				oos.writeObject(m);				 				      
            			    } catch (Exception e) {
            				    e.printStackTrace();
            				   // TODO: handle exception			    
            			    }
                    }
                    else
                    {
                        dtde.rejectDrop();//否则拒绝拖拽来的数据
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
	public void setjiluText(String str)
	{
		k.setText(str);
	}
}

