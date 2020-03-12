package com.qq.client.view;

import com.qq.client.tools.ManageClientConServerThread;
import com.qq.common.Message;
import com.qq.common.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.ObjectOutputStream;

public class SearchFriend extends JFrame implements ActionListener{
	private JTextField textField;
	private boolean isDragged = false;
	private Point loc = null;  
	JButton sf_b4,sf_b1;
    private Point tmp = null;
    String ownerId;
	String friendId;
	public SearchFriend(String ownerId) {
		this.setSize(400,200);
		this.ownerId=ownerId;
		this.setUndecorated(false);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(22, 86, 313, 32);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel sf_label1 = new JLabel(new ImageIcon("image/search/QQsearch.png"));
		sf_label1.setBounds(0, 0, 56, 20);
		panel.add(sf_label1);
		
		sf_b1 = new JButton(new ImageIcon("image/search/fexit.png"));
		sf_b1.addActionListener(this);
		sf_b1.setBounds(372, 0, 28, 28);
		panel.add(sf_b1);
		
		JButton sf_b2 = new JButton(new ImageIcon("image/search/fmin.png"));
		sf_b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setExtendedState(JFrame.ICONIFIED);	
			}
		});
		sf_b2.setBounds(345, 0, 28, 28);
		panel.add(sf_b2);
		
		JButton sf_b3 = new JButton(new ImageIcon("image/search/shuaxin.png"));
		sf_b3.setBounds(318, 0, 28, 28);
		panel.add(sf_b3);
		
		sf_b4 = new JButton(new ImageIcon("image/search/searchimage.png"));
		sf_b4.setBounds(335, 86, 38, 32);
		sf_b4.addActionListener(this);
		panel.add(sf_b4);
		
		JLabel sf_label2 = new JLabel(new ImageIcon("image/search/beijing.png"));
		sf_label2.setBounds(0, 0, 400,200);
		panel.add(sf_label2);
		
		this.setUndecorated(true);
		this.setVisible(true);
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

            	if(e.getY()<28)
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
		// TODO Auto-generated method stub
	//	SearchFriend searchfriend = new SearchFriend("s");
	}

	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
		if(e.getSource()==sf_b1)
		{
			this.dispose();	
		}
		if(e.getSource()==sf_b4)
		{
			//����û�����ˣ����Ͱ�ť
			Message m=new Message();
			m.setMesType(MessageType.message_ret_addFriend);
			m.setSender(this.ownerId);
			m.setGetter(textField.getText());
			m.setCon(textField.getText());
			m.setSendTime(new java.util.Date().toString());
			//���͸�������.
			try {
					ObjectOutputStream oos=new ObjectOutputStream
							(ManageClientConServerThread.getClientConServerThread(ownerId).getS().getOutputStream());
					oos.writeObject(m);
			} catch (Exception e1) {
				e1.printStackTrace();
				// TODO: handle exception
			}
			
			JOptionPane.showMessageDialog(this,"���������ѷ���");
			this.dispose();
		}
	}
}
