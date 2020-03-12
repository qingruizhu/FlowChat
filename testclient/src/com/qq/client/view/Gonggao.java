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

public class Gonggao  extends JFrame implements ActionListener{
	JButton exit,min;
	Message ms;
	private boolean isDragged = false;
	private Point loc = null;  

    private Point tmp = null;
	
	public Gonggao(Message m) {
		this.ms = m;
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u7CFB\u7EDF\u516C\u544A\uFF1A");
		lblNewLabel.setBounds(10, 26, 107, 26);
		getContentPane().add(lblNewLabel);
		
		exit = new JButton(new ImageIcon("image/fexit.png"));
		exit.addActionListener(this);
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
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 52, 180, 88);
		
		textArea.setText(ms.getCon());
		JScrollPane scrollPane_2 = new JScrollPane(textArea);
		scrollPane_2.setBounds(10, 52, 180, 88);
		scrollPane_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		scrollPane_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		this.setUndecorated(true);
		getContentPane().add(scrollPane_2);
		this.setBounds(1166, 574, 200, 150);
		
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

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Gonggao m = new Gonggao(new Message());
	}

	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(e.getSource()== exit)
		{
			this.dispose();
		}
	}

	
}
