package com.qq.client.view;

import com.qq.client.tools.*;
import com.qq.common.Message;
import com.qq.common.MessageType;

import javax.swing.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.ObjectOutputStream;

public class AccepVideo  extends JFrame implements ActionListener{
	JButton yes,no,exit,min;
	Message ms;
	private boolean isDragged = false;
	private Point loc = null;  

    private Point tmp = null;
	
	public AccepVideo(Message m) {
		this.ms = m;
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(ms.getSender()+" ���������");
		lblNewLabel.setBounds(30, 53, 146, 33);
		getContentPane().add(lblNewLabel);
		
		yes = new JButton("ͬ��");
		yes.addActionListener(this);
		yes.setBounds(24, 96, 66, 23);
		yes.setOpaque(true);
		yes.setBackground(Color.white);
		getContentPane().add(yes);
		
		no = new JButton("�ܾ�");
		no.addActionListener(this);
		no.setBounds(110, 96, 66, 23);
		no.setOpaque(true);
		no.setBackground(Color.white);
		getContentPane().add(no);
		
		exit = new JButton(new ImageIcon("image/fexit.png"));
		exit.setBounds(174, 0, 26, 26);
		getContentPane().add(exit);
		
		min = new JButton(new ImageIcon("image/fmin.png"));
		min.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);
			}
		});
		min.setBounds(148, 0, 26, 26);
		getContentPane().add(min);
		
		JLabel lblNewLabel_1 = new JLabel(new ImageIcon("image/beijing.png"));
		lblNewLabel_1.setBounds(0, 0, 200, 26);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel();
		lblNewLabel_2.setBounds(0, 26, 200, 122);
		lblNewLabel_2.setOpaque(true);
		lblNewLabel_2.setBackground(Color.white);
		getContentPane().add(lblNewLabel_2);
		this.setUndecorated(true);
		
		this.setBounds(1166, 574, 200, 150);
		
		this.setVisible(true);
		
		this.setTitle("������Ƶ����");
		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 510) / 2;  

        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 290) / 2;  
        // Ϊ�����������¼�  

        this.addMouseListener(new java.awt.event.MouseAdapter() {  

            public void mouseReleased(MouseEvent e) {  

                isDragged = false;  

                // Ϊָ���Ĺ�����ù��ͼ��  

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

            // ��갴��������ϰ��²��϶�ʱ���á�  

            public void mouseDragged(MouseEvent e) {  
            	
                if (isDragged) {  

                    loc = new Point(getLocation().x + e.getX() - tmp.x,  

                            getLocation().y + e.getY() - tmp.y);  

                    setLocation(loc);  

                }  

            }  

        });
	}

	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		AccepVideo m = new AccepVideo(new Message());
	}

	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==yes)
		{
			
//			new VAplay(ms.getMyport(),ms.getFriendport(),ms.getAddress());
			//����û�����ˣ����Ͱ�ť
			Message m=new Message();
			m.setMesType(MessageType.message_acceptvdo);
			m.setSender(ms.getSender());
			m.setGetter(ms.getGetter());
			m.setMyport(ms.getFriendport());
			m.setFriendport(ms.getMyport());
			m.setCon("yes");
			m.setSendTime(new java.util.Date().toString());
			//���͸�������.
			try {
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(ms.getGetter()).getS().getOutputStream());
					oos.writeObject(m);
			} catch (Exception e1) {
				e1.printStackTrace();
				// TODO: handle exception
			}	
			this.dispose();
		}
		if(e.getSource()==no)
		{
			Message m=new Message();
			m.setMesType(MessageType.message_jujuevdo);
			m.setSender(ms.getSender());
			m.setGetter(ms.getGetter());
			m.setMyport(ms.getFriendport());
			m.setFriendport(ms.getMyport());
			m.setCon("yes");
			m.setSendTime(new java.util.Date().toString());
			//���͸�������.
			try {
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(ms.getGetter()).getS().getOutputStream());
					oos.writeObject(m);
			} catch (Exception e1) {
				e1.printStackTrace();
				// TODO: handle exception
			}	
			this.dispose();
		}
	}
}
