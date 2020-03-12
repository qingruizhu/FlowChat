/**
 * 我的好友列表,(也包括陌生人，黑名单)
 */
package com.qq.client.view;






import com.qq.client.model.QqClientUser;
import com.qq.client.tools.*;
import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.common.User;

import javax.swing.*;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import java.util.*;

public class QqFriendList extends JFrame implements ActionListener,MouseListener,WindowListener,TreeSelectionListener,TreeModelListener {
	private boolean isDragged = false;
	private Point loc = null;  
	JPanel cp;
	JLabel lblNewLabel_1=null;
    private Point tmp = null;
    private JTextField textField;
    private JTextField textField_1;
    static String con = null,con1 = null;
	Message moutline;
	String flag = "";
	DefaultMutableTreeNode liebiao[];
	JTree jtree; 
	DefaultMutableTreeNode root; 
	DefaultTreeModel treeModel=null;
	String owner;
	String myname;
	JButton btnNewButton,btnNewButton_1,btnNewButton_2,btnNewButton_3,btnJb,btnJb_1,btnNewButton_4;
	JButton btnJb_2,btnJb_3,btnJb_4,btnB,btnB_1,btnB_2,btnB_3,btnB_4,btnB_5,btnB_6,btnB_7,btnB_8;
	//更新在线的好友情况
	//////////////////////////////////////////
	DefaultMutableTreeNode top = new DefaultMutableTreeNode("QQ群");
	DefaultMutableTreeNode qun;
	String QunId;
	/////////////////////////////////////////
		public void upateFriend(Message m)
		{
		//	JOptionPane.showMessageDialog(this,this.owner+"的对话框"+m.getCon()+"上线了  con是"+con+" con1是"+con1);
			if(con1 != null)
			{
				if(con1.contains(m.getCon()))
				{
					int i = con1.indexOf(m.getCon());
					String s1 = con1.substring(0, i-1);
					String s2 = con1.substring(i+m.getCon().length());
					con1 = s1+s2;
					System.out.println(con1);
				}
				
			}
			con = con +" "+m.getCon();
			m.setCon(con);
			m.setMesType(MessageType.message_get_onLineFriend);
			this.jtree.setCellRenderer(new MyDefaultTreeCellRenderer(this.myname,m));

		}
		
		public void upateunloadFriend(Message m)
		{
		//	JOptionPane.showMessageDialog(this,this.owner+"的对话框"+m.getCon()+"下线了  con是"+con+" con1是"+con1);
			con1 = con1 +" "+m.getCon();
			m.setCon1(m.getCon());
			m.setCon(con);
			m.setCon1(con1);
			m.setMesType(MessageType.messgae_ret_unloadFriends);
			this.jtree.setCellRenderer(new MyDefaultTreeCellRenderer(this.myname,m));
			
		//	this.jtree.getCellRenderer().getTreeCellRendererComponent(this.jtree, jtree, true, true, true, row, true);
			//upateFriend(Message m)
			/*String onLineFriend[]=m.getCon().split(" ");
			JLabel lab;
			
			for(int i=0;i<onLineFriend.length;i++)
			{
				lab = (JLabel)friendlist.get(onLineFriend[i]);
				if(lab != null)
					lab.setEnabled(false);
			}*/
		}
		
		
		public void init_update(Message m)
		{
			

			//获取选中节点  
	        DefaultMutableTreeNode selectedNode  = liebiao[0];  
	        //如果节点为空，直接返回  
	        if (selectedNode == null) return;  
	        //获取该选中节点的父节点  
	        DefaultMutableTreeNode parent  = liebiao[0];  
	        //如果父节点为空，直接返回  
	        if (parent == null) return;  
	        //创建一个新节点  
	        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(m.getFriname());  
	        //获取选中节点的选中索引  
	        int selectedIndex = parent.getIndex(selectedNode);  
	        //在选中位置插入新节点  
	        treeModel.insertNodeInto(newNode, parent, selectedIndex + 1);  
		}
		
		public void createTree (DefaultMutableTreeNode root,String s[][]) 
		{ 
		
			DefaultMutableTreeNode liebiao = null;
			for(int i = 0;i<s.length;i++)
			{
				if(s[i][1].equals(root.getUserObject().toString()))
				{
					liebiao = new DefaultMutableTreeNode(s[i][0]);
					root.add(liebiao);
				}
			}
		} 
		
	
	public QqFriendList(String OwnId, Message s) {
		
		this.owner = OwnId;
		addWindowListener(this);
		this.setSize(277,660);
		this.myname = s.getMyname();
		JPanel panel = new JPanel();
		
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		btnNewButton = new JButton(new ImageIcon("image/friendlist/fexit.png"));
		btnNewButton.addActionListener(this);
				
		
		btnNewButton.setBounds(247, 0, 30, 30);
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton(new ImageIcon("image/friendlist/fmin.png"));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);	
			}
		});
		btnNewButton_1.setBounds(217, 0, 30, 30);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel(new ImageIcon("image/friendlist/biaoti.png"));
		lblNewLabel.setBounds(0, 0, 44, 21);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(0, 134, 247, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		btnNewButton_2 = new JButton(new ImageIcon("image/friendlist/search.png"));
		btnNewButton_2.setBounds(247, 134, 30, 23);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel(this.myname);
		lblNewLabel_3.setBounds(110, 32, 68, 21);
		panel.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(110, 63, 167, 21);
		panel.add(textField_1);
		this.setForeground(getBackground());
		textField_1.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(175, 32, 63, 21);
		panel.add(comboBox);
		
		btnNewButton_3 = new JButton(new ImageIcon("image/friendlist/tubiao1.png"));
		btnNewButton_3.setBounds(0, 111, 20, 23);
		panel.add(btnNewButton_3);
		
		btnJb = new JButton(new ImageIcon("image/friendlist/tubiao2.png"));
		btnJb.setBounds(30, 111, 20, 23);
		panel.add(btnJb);
		
		btnJb_1 = new JButton(new ImageIcon("image/friendlist/tubiao3.png"));
		btnJb_1.setBounds(60, 111, 20, 23);
		panel.add(btnJb_1);
		
		btnNewButton_4 = new JButton(new ImageIcon("image/friendlist/tubiao4.png"));
		btnNewButton_4.setBounds(88, 111, 20, 23);
		panel.add(btnNewButton_4);
		
		btnJb_2 = new JButton(new ImageIcon("image/friendlist/tubiao5.png"));
		btnJb_2.setBounds(118, 111, 20, 23);
		panel.add(btnJb_2);
		
		btnJb_3 = new JButton(new ImageIcon("image/friendlist/tubiao6.png"));
		btnJb_3.setBounds(148, 111, 20, 23);
		panel.add(btnJb_3);
		
		btnJb_4 = new JButton(new ImageIcon("image/friendlist/tubiao7.png"));
		btnJb_4.setBounds(175, 111, 20, 23);
		panel.add(btnJb_4);
		
		lblNewLabel_1 = new JLabel(new ImageIcon("image/friendlist/Qtouxiang.jpg"));
		lblNewLabel_1.setBounds(10, 23, 78, 78);
		lblNewLabel_1.addMouseListener(new MouseListener(){public void mouseClicked(MouseEvent arg0) {
			// TODO 自动生成的方法存根
			if(arg0.getSource()==lblNewLabel_1)
			{
				Message m=new Message();
				m.setMesType(MessageType.message_data);
				m.setSender(owner);
				try {
					ObjectOutputStream oos=new ObjectOutputStream
					(ManageClientConServerThread.getClientConServerThread(owner).getS().getOutputStream());
					oos.writeObject(m);
				} catch (Exception e) {
					e.printStackTrace();
     		}			
			}}
		public void mousePressed(MouseEvent e) {			
		}
		public void mouseReleased(MouseEvent e) {		
		}
		public void mouseEntered(MouseEvent e) {
		}
		public void mouseExited(MouseEvent e) {
		}});
		panel.add(lblNewLabel_1);
		
		JLabel label = new JLabel(new ImageIcon("image/friendlist/beijing.png"));
		label.setBounds(0, 0, 277, 156);
		panel.add(label);
		
		btnB = new JButton(new ImageIcon("image/friendlist/mainmenue.png"));
		btnB.setBounds(0, 629, 30, 23);
		panel.add(btnB);
		
		btnB_1 = new JButton(new ImageIcon("image/friendlist/shezhi.png"));
		btnB_1.setBounds(30, 629, 30, 23);
		panel.add(btnB_1);
		
		btnB_2 = new JButton(new ImageIcon("image/friendlist/messagemanage.png"));
		btnB_2.setBounds(60, 629, 30, 23);
		panel.add(btnB_2);
		
		btnB_3 = new JButton(new ImageIcon("image/friendlist/filehleper.png"));
		btnB_3.setBounds(88, 629, 30, 23);
		panel.add(btnB_3);
		
		btnB_4 = new JButton(new ImageIcon("image/friendlist/shoucang.png"));
		btnB_4.setBounds(118, 629, 30, 23);
		panel.add(btnB_4);
		
		btnB_5 = new JButton(new ImageIcon("image/friendlist/apl.png"));
		btnB_5.setBounds(199, 629, 62, 23);
		panel.add(btnB_5);
		
		btnB_6 = new JButton(new ImageIcon("image/friendlist/search1.png"));
		btnB_6.addActionListener(this);
		btnB_6.setBounds(148, 629, 48, 23);
		panel.add(btnB_6);
		
		btnB_7 = new JButton(new ImageIcon("image/friendlist/tubiao8.png"));
		btnB_7.setBounds(0, 604, 30, 23);
		panel.add(btnB_7);
		
		btnB_8 = new JButton(new ImageIcon("image/friendlist/tubiao9.png"));
		btnB_8.setBounds(30, 604, 30, 23);
		panel.add(btnB_8);
		
		cp = new JPanel();
		cp.setLayout(new BorderLayout()); 
		cp.setBounds(0, 155, 277, 449);
		liebiao = new DefaultMutableTreeNode[Integer.parseInt(s.getFenzucount())];
		String sa = s.getCon();
		String friend[]=sa.split(" ");
		String friend1[][] = new String[friend.length][];
		for(int i =0; i < friend.length; i++)
		{
			friend1[i] = friend[i].split("-");
		}
		int p = 0;
		for(int i = 0;i<friend1.length;i++)
		{
			int j;
			for( j = 0; j<i; j++)
			{
				if(friend1[j][1].equals(friend1[i][1]))
					break;
			}
			if(j == i)
				liebiao[p++] = new DefaultMutableTreeNode(friend1[i][1]); 
						
		}   
		root=new DefaultMutableTreeNode(""); 
		 
		for(int i = 0;i<liebiao.length;i++)
        {
			root.add(liebiao[i]);
			createTree(liebiao[i], friend1);
		}
		// /////////////////////////////
		qun = new DefaultMutableTreeNode("软件工程(100000)");
		top.add(qun);
		root.add(top);
		// ////////////////////////////////
		jtree = new JTree(root);
		jtree.setEditable(true);
		jtree.addMouseListener(this); 
		treeModel=(DefaultTreeModel)jtree.getModel(); 
		treeModel.addTreeModelListener(this); 
		JScrollPane scrollPane=new JScrollPane(); 
		scrollPane.setViewportView(jtree); 
		
		/*jtree=new JTree(root); 
		jsp1=new JScrollPane(jtree);*/
		
		//设置自定义描述类  
		String fir[] = new String[1];
		
        jtree.setCellRenderer(new MyDefaultTreeCellRenderer(this.myname,s));  
        jtree.addMouseListener(this);
        cp.add(scrollPane,BorderLayout.CENTER); 
	//	cp.add(jphy_addnewfriend,BorderLayout.SOUTH);
		panel.add(cp);
		this.setUndecorated(true);
		
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

            	if(e.getY()<23)
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
        /////////////////////////////查询是否有好友请求
        moutline=new Message();
        moutline.setMesType(MessageType.message_hasfriendask);
        moutline.setSender(this.owner);
        moutline.setSendTime(new java.util.Date().toString());
        //发送给服务器.
        try {
        	ObjectOutputStream oos=new ObjectOutputStream
        			(ManageClientConServerThread.getClientConServerThread(owner).getS().getOutputStream());
        	oos.writeObject(moutline);
        } catch (Exception e) {
        	e.printStackTrace();
        }
        ///////////////////////////////

        moutline=new Message();
        moutline.setMesType(MessageType.message_ret_pointMessage);
        moutline.setSender(this.owner);
        moutline.setSendTime(new java.util.Date().toString());
        //发送给服务器.
        try {
        	ObjectOutputStream oos=new ObjectOutputStream
        			(ManageClientConServerThread.getClientConServerThread(owner).getS().getOutputStream());
        	oos.writeObject(moutline);
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 //new QqFriendList("sf",new Message()).setVisible(true);
	}

	public void treeNodesChanged(TreeModelEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void treeNodesInserted(TreeModelEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void treeNodesRemoved(TreeModelEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void treeStructureChanged(TreeModelEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void valueChanged(TreeSelectionEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void windowOpened(WindowEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void windowClosing(WindowEvent e) {
		// TODO 自动生成的方法存根
		QqClientUser qqClientUser=new QqClientUser();
		User u=new User();
		u.setUserId(owner);
		u.setUnload("1234567");
		u.setPasswd("2");
		u.setRegiste("1");
		qqClientUser.unload(u);
		ManageClientConServerThread.getClientConServerThread(this.owner).stop();
		ManageClientConServerThread.RemoveClientConServerThread(this.owner);
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

	public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getClickCount()==2&&!((DefaultMutableTreeNode)(((JTree)arg0.getSource()).getLastSelectedPathComponent())).toString().equals("软件工程(100000)"))
		{		
			JTree tree = (JTree)arg0.getSource();  
			//获取目前选取的节点  
			DefaultMutableTreeNode selectionNode =  
            (DefaultMutableTreeNode)tree.getLastSelectedPathComponent(); 
			if(selectionNode.isLeaf())
			{
				//得到该好友的编号
				QqChat qqChat1;
				String friendNo = selectionNode.toString(); 
				int x = friendNo.indexOf("(");
		        friendNo = friendNo.substring(x+1, friendNo.length()-1);
				qqChat1 = ManageQqChat.getQqChat(this.owner+" "+friendNo);
				if(qqChat1 == null)
				{
					QqChat qqChat=new QqChat(this.owner,friendNo);
					//把聊天界面加入到管理类
					ManageQqChat.addQqChat(this.owner+" "+friendNo, qqChat);
				}
				
				if(flag.indexOf(friendNo)==-1)
				{
					Message m=new Message();
					m.setMesType(MessageType.message_ret_outlineMessage);
					m.setSender(this.owner);
					m.setGetter(friendNo);
					//m.setCon(jtf.getText());
					m.setSendTime(new java.util.Date().toString());
					//发送给服务器.
					try {
						ObjectOutputStream oos=new ObjectOutputStream
								(ManageClientConServerThread.getClientConServerThread(owner).getS().getOutputStream());
						oos.writeObject(m);
					} catch (Exception e1) {
						e1.printStackTrace();
						// TODO: handle exception
					}
					flag+=friendNo;
				}
			}
			
		}
		if(arg0.getClickCount()==2&&((DefaultMutableTreeNode)(((JTree)arg0.getSource()).getLastSelectedPathComponent())).toString().equals("软件工程(100000)"))
			{
				JTree tree = (JTree)arg0.getSource();  
				//获取目前选取的节点  
				 DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent(); 
				 if(selectionNode.isLeaf())
				 {
					//得到该好友的编号
					QunChat qqChat1;
					String QunId = selectionNode.toString(); 
					int x = QunId.indexOf("(");
					QunId = QunId.substring(x+1, QunId.length()-1);
					qqChat1 = ManageQqChat.getQunChat(this.owner+"  "+QunId);
					if(qqChat1 == null)
					{
						QunChat qqChat=new QunChat(this.owner,QunId);
						//把聊天界面加入到管理类
						ManageQqChat.addQunChat(this.owner+"  "+QunId, qqChat);
						Message m=new Message();
						m.setMesType(MessageType.message_getQunPeople);
						m.setSender(this.owner);
						m.setGetter(QunId);
						
						
						m.setSendTime(new java.util.Date().toString());
						
						//发送给服务器.
						try {
							ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(owner).getS().getOutputStream());
							oos.writeObject(m);
							
						} catch (Exception e) {
							e.printStackTrace();
							// TODO: handle exception
						}	
					}
					else
					{
						qqChat1.setVisible(true);
					}
				 }
			}
	}

	public void mousePressed(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		JTree tree = (JTree)arg0.getSource();  
		//创建节点绘制对象  
        DefaultTreeCellRenderer cellRenderer =  
                            (DefaultTreeCellRenderer)tree.getCellRenderer();  
        //设置字体  
        cellRenderer.setForeground(Color.red);
        cellRenderer.setBackgroundNonSelectionColor(Color.white);  
        cellRenderer.setBackgroundSelectionColor(Color.yellow);  
        cellRenderer.setBorderSelectionColor(Color.red); 
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		JTree tree = (JTree)arg0.getSource();  
		//创建节点绘制对象  
        DefaultTreeCellRenderer cellRenderer =  
                            (DefaultTreeCellRenderer)tree.getCellRenderer();  
		 cellRenderer.setForeground(Color.black);
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO 自动生成的方法存根
		if(arg0.getSource()==btnNewButton)
		{
			QqClientUser qqClientUser=new QqClientUser();
			User u=new User();
			u.setUserId(owner);
			u.setUnload("1234567");
			u.setPasswd("2");
			u.setRegiste("1");
			qqClientUser.unload(u);
			this.dispose();	
			ManageClientConServerThread.getClientConServerThread(this.owner).stop();
			ManageClientConServerThread.RemoveClientConServerThread(this.owner);
		}
		if(arg0.getSource()==btnB_6)
		{
			new SearchFriend(this.owner);
		}
	}
}
