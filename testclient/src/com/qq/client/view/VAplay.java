//package com.qq.client.view;
//
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGEncodeParam;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//import javax.imageio.ImageIO;
//import javax.media.*;
//import javax.media.control.*;
//import javax.media.format.*;
//import javax.media.protocol.*;
//import javax.media.util.*;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.net.DatagramPacket;
//import java.net.DatagramSocket;
//import java.net.InetAddress;
//import java.util.Vector;
//
//
//public class VAplay extends JFrame implements Runnable,WindowListener,ActionListener{
//	// 定义视频图像播放器
//
//			private static Player player = null;
//
//			// 定义音频播放器
//
//			private static Player player2 = null;
//
//			// 获取视频设备
//
//			private CaptureDeviceInfo device = null;
//
//			// 获取音频设备
//
//			private CaptureDeviceInfo device2 = null;
//
//			// 媒体定位器
//
//			private MediaLocator locator = null;
//
//			private Image image;
//
//			private Buffer buffer = null;
//
//			private BufferToImage b2i = null;
//
//			// 设置摄像头驱动类型
//
//			String str = "vfw:Microsoft WDM Image Capture (Win32):0";
//
//			// 定义播放组件变量
//
//			Component comV, comVC, comA;
//
//	//顶部
//	JButton v_min,v_max,v_close;
//	JLabel v_biaoti;
//	//下部
//	JButton v_chat,v_close1,v_closesxt,v_bingpaiImage,v_photo,v_makeafun;
//	private boolean isDragged = false;
//	private Point loc = null;
//	private Point tmp = null;
//
//
//
//	JPanel panel_1,panel,panel_2,panel_3;
//	Thread thread1, thread2;
//
//	int myport; int friendport;InetAddress address;
//	private JLabel lab= new JLabel(new ImageIcon("image//load.gif"));
//
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//	//	Vedio vedio = new Vedio();
//
//
//	}
//	public VAplay(int myport, int friendport,InetAddress address){
//		this.setSize(740, 424);
//
//		this.setUndecorated(true);
//		getContentPane().setLayout(null);
//
//		panel_1 = new JPanel();
//		panel_1.setBounds(0, 0, 740, 30);
//		getContentPane().add(panel_1);
//		panel_1.setLayout(null);
//		//背景颜色
//
//		this.addWindowListener(this);
//		this.myport = myport;
//		this.friendport = friendport;
//		this.address = address;
//		String ip=address.getHostAddress().toString();
//
//
//		v_biaoti = new JLabel("正在和XXX视频通话");
//		v_biaoti.setForeground(Color.white);
//
//		v_biaoti.setBounds(0, 0, 173, 30);
//		panel_1.add(v_biaoti);
//
//		v_min = new JButton(new ImageIcon("image/vedio/min.jpg"));
//		v_min.setBounds(656, 0, 28, 28);
//		panel_1.add(v_min);
//
//		v_max = new JButton(new ImageIcon("image/vedio/max.jpg"));
//		v_max.setBounds(684, 0, 28, 28);
//		panel_1.add(v_max);
//
//		v_close = new JButton(new ImageIcon("image/vedio/close.jpg"));
//		v_close.setBounds(712, 0, 28, 28);
//		v_close.addActionListener(this);
//		panel_1.add(v_close);
//		JLabel back1 = new JLabel(new ImageIcon("image/vedio/back.png"));
//		back1.setBounds(0, 0, 740, 30);
//		panel_1.add(back1);
//
//		panel = new JPanel();
//		panel.setBounds(0, 388, 740, 36);
//		getContentPane().add(panel);
//		panel.setLayout(null);
//
//		v_chat = new JButton(new ImageIcon("image/vedio/openchat.jpg"));
//		v_chat.setBounds(10, 4, 38, 32);
//		panel.add(v_chat);
//
//		v_close1 = new JButton(new ImageIcon("image/vedio/close1.jpg"));
//		v_close1.setBounds(654, 4, 58, 22);
//		v_close1.addActionListener(this);
//		panel.add(v_close1);
//
//		v_closesxt = new JButton(new ImageIcon("image/vedio/closesxt.jpg"));
//		v_closesxt.setBounds(110, 4, 30, 30);
//		panel.add(v_closesxt);
//
//		v_photo = new JButton(new ImageIcon("image/vedio/photo.jpg"));
//		v_photo.setBounds(200, 4, 30, 30);
//		panel.add(v_photo);
//
//		v_makeafun = new JButton(new ImageIcon("image/vedio/makeafun.jpg"));
//		v_makeafun.setBounds(160, 4, 30, 30);
//		panel.add(v_makeafun);
//
//		v_bingpaiImage = new JButton(new ImageIcon("image/vedio/bingpai.jpg"));
//		v_bingpaiImage.setBounds(240, 4, 30, 30);
//		panel.add(v_bingpaiImage);
//
//		JLabel back2 = new JLabel(new ImageIcon("image/vedio/back.png"));
//		back2.setBounds(0, 0, 740, 36);
//		panel.add(back2);
//
//		panel_2 = new JPanel();
//		panel_2.setBounds(0, 29, 740, 359);
//		getContentPane().add(panel_2);
//		panel_2.setLayout(null);
//
//
//		panel_3 = new JPanel();
//		panel_3.setBounds(361, 57, 340, 240);
//		panel_2.add(panel_3);
//
//		lab.setBounds(0, 57, 340, 240);
//		panel_2.add(lab);
//		JLabel back3 = new JLabel(new ImageIcon("image/vedio/back.png"));
//		back3.setBounds(0, 0, 740, 359);
//		panel_2.add(back3);
//
//		this.setVisible(true);
//		int w = (Toolkit.getDefaultToolkit().getScreenSize().width - 510) / 2;
//
//        int h = (Toolkit.getDefaultToolkit().getScreenSize().height - 290) / 2;
//
//        try
//
//		{
//
//			// 在本地播放视频
//
//			jbInit();
//
//			// 在本地播放音频
//
//			speaker();
//
//		}
//
//		catch (Exception e)
//
//		{
//
//			e.printStackTrace();
//
//		}
//
//		thread1 = new Thread(this);
//
//		thread2 = new Thread(this);
//
//		// 负责接收对方数据
//
//		thread1.start();
//
//		// 负责向对方发送数据
//
//		thread2.start();
//
//
//
//        // 为窗体添加鼠标事件
//
//        this.addMouseListener(new java.awt.event.MouseAdapter() {
//
//            public void mouseReleased(MouseEvent e) {
//
//                isDragged = false;
//
//                // 为指定的光标设置光标图像
//
//                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
//
//            }
//
//
//
//            public void mousePressed(MouseEvent e) {
//
//            	if(e.getY()<30)
//            	{
//                tmp = new Point(e.getX(), e.getY());
//
//                isDragged = true;
//
//                setCursor(new Cursor(Cursor.MOVE_CURSOR));
//
//            }
//            }
//
//        });
//
//
//
//        this.addMouseMotionListener(new MouseMotionAdapter() {
//
//            // 鼠标按键在组件上按下并拖动时调用。
//
//            public void mouseDragged(MouseEvent e) {
//
//                if (isDragged) {
//
//                    loc = new Point(getLocation().x + e.getX() - tmp.x,
//
//                            getLocation().y + e.getY() - tmp.y);
//
//                    setLocation(loc);
//
//                }
//
//            }
//
//        });
//
//	}
//
//	// 在本地播放视频
//
//		private void jbInit() throws Exception
//
//		{
//
//			// 初始化设备，str为设备驱动
//
//			device = CaptureDeviceManager.getDevice(str);
//
//			// 确定所需的协议和媒体资源的位置
//
//			locator = device.getLocator();
//
//			try
//
//			{
//
//				// 调用sethint后Manager会尽力用一个能和轻量级组件混合使用的Renderer来创建播放器
//
//				Manager.setHint(Manager.LIGHTWEIGHT_RENDERER, new Boolean(true));
//
//				// 通过管理器创建播放线程使player达到Realized状态
//
//				player = Manager.createRealizedPlayer(locator);
//
//				player.start();
//
//				if ((comV = player.getVisualComponent()) != null)
//
//				// player.getVisualComponent()是一个播放视频媒体组件。
//
//				{
//
//					panel_3.add(comV, "Center");
//
//				}
//
//				if ((comVC = player.getControlPanelComponent()) != null)
//
//				// player.getControlPanelComponent()是显示时间的组件
//
//				{
//
//					panel_3.add(comVC, "South");
//
//				}
//
//			}
//
//			catch (Exception e)
//
//			{
//
//				e.printStackTrace();
//
//			}
//
//			setBounds(0, 0, 740, 428);
//
//			setVisible(true);
//
//			int new_w = panel_3.getWidth(); // 输出的图像宽度
//
//			int new_h = panel_3.getHeight(); // 输出的图像高度
//
//			// MediaTracker类跟踪一个Image对象的装载，完成图像加载
//
//			MediaTracker mt = new MediaTracker(this.panel_3);
//
//			try
//
//			{
//
//				mt.addImage(image, 0);// 装载图像
//
//				mt.waitForID(0);// 等待图像全部装载
//
//			}
//
//			catch (Exception e)
//
//			{
//
//				e.printStackTrace();
//
//			}
//
//			// 将图像信息写入缓冲区
//
//			BufferedImage buffImg = new BufferedImage(new_w, new_h,
//					BufferedImage.TYPE_INT_RGB);
//
//			Graphics g = buffImg.createGraphics();
//
//			g.drawImage(image, 0, 0, new_w, new_h, this.panel_3);
//
//			g.dispose();
//
//		}
//
//		// 在本地播放音频
//
//		private void speaker() throws Exception
//
//		{
//
//			Vector deviceList = CaptureDeviceManager.getDeviceList(new
//
//			AudioFormat(AudioFormat.LINEAR, 44100, 16, 2));
//
//			if (deviceList.size() > 0)
//
//			{
//
//				device2 = (CaptureDeviceInfo) deviceList.firstElement();
//
//			}
//
//			else
//
//			{
//
//				System.out.println("找不到音频设备！");
//
//			}
//
//			try
//
//			{
//
//				player2 = Manager.createRealizedPlayer(device2.getLocator());
//
//				player2.start();
//
//				if ((comA = player2.getControlPanelComponent()) != null)
//
//				{
//
//				//	p3.add(comA, "South");
//
//				}
//
//			}
//
//			catch (Exception e)
//
//			{
//
//				e.printStackTrace();
//
//			}
//
//		}
//
//		// 线程，接收或发送视频图像数据
//
//		public void run()
//
//		{
//
//			DatagramPacket pack = null;
//
//			DatagramSocket maildata = null;
//
//			byte data[] = new byte[320 * 240];
//
//			try
//
//			{
//
//				// 定义数据包
//
//				pack = new DatagramPacket(data, data.length);
//
//				// 定义数据报接收包
//
//				maildata = new DatagramSocket(this.myport);
//
//			}
//
//			catch (Exception e) {
//			}
//
//			while (true)
//
//			{
//
//				if (Thread.currentThread() == thread1)
//
//				{
//
//					if (maildata == null)
//
//					{
//
//						break;
//
//					}
//
//					else
//
//					{
//
//						try
//
//						{
//
//							// 接收
//
//							maildata.receive(pack);
//
//							ByteArrayInputStream input = new ByteArrayInputStream(
//									data);
//
//							Image message = ImageIO.read(input);
//
//							// 在接收图像窗口显示视频图像
//
//							lab.setIcon(new ImageIcon(message));
//
//						}
//
//						catch (Exception e)
//
//						{
//
//							System.out.println("接收图像数据失败！");
//
//						}
//
//					}
//
//				}
//
//				else if (Thread.currentThread() == thread2)
//
//				{
//
//					try
//
//					{
//
//						// 捕获要在播放窗口显示的图象帧
//
//						FrameGrabbingControl fgc = (FrameGrabbingControl) player
//								.getControl(
//
//								"javax.media.control.FrameGrabbingControl");
//
//						// 获取当前祯并存入Buffer类
//
//						buffer = fgc.grabFrame();
//
//						b2i = new BufferToImage((VideoFormat) buffer.getFormat());
//
//						image = b2i.createImage(buffer); // 转化为图像
//
//						// 创建image图像对象大小的图像缓冲区
//
//						BufferedImage bi = (BufferedImage) createImage(
//								image.getWidth(null),
//
//								image.getHeight(null));
//
//						// 根据BufferedImage对象创建Graphics2D对象
//
//						Graphics2D g2 = bi.createGraphics();
//
//						g2.drawImage(image, null, null);
//
//						ByteArrayOutputStream output = new ByteArrayOutputStream();
//
//						// 转换成JPEG图像格式
//
//						JPEGImageEncoder encoder = JPEGCodec
//								.createJPEGEncoder(output);
//
//						JPEGEncodeParam jpeg = encoder
//								.getDefaultJPEGEncodeParam(bi);
//
//						jpeg.setQuality(0.5f, false);
//
//						encoder.setJPEGEncodeParam(jpeg);
//
//						encoder.encode(bi);
//
//						output.close();
//
//
//
//						DatagramPacket datapack1 = new DatagramPacket(
//								output.toByteArray(), output.size(),
//
//								this.address, this.friendport);
//
//						DatagramSocket maildata1 = new DatagramSocket();
//
//						maildata1.send(datapack1);
//
//						Thread.sleep(400);
//
//					}
//
//					catch (Exception e)
//
//					{
//
//						System.out.println("视频发送失败！");
//
//					}
//
//				}
//
//			}
//		}
//
//
//	public void windowOpened(WindowEvent e) {
//		// TODO 自动生成的方法存根
//
//	}
//	public void windowClosing(WindowEvent e) {
//		// TODO 自动生成的方法存根
//
//	}
//	public void windowClosed(WindowEvent e) {
//		// TODO 自动生成的方法存根
//
//	}
//	public void windowIconified(WindowEvent e) {
//		// TODO 自动生成的方法存根
//
//	}
//	public void windowDeiconified(WindowEvent e) {
//		// TODO 自动生成的方法存根
//
//	}
//	public void windowActivated(WindowEvent e) {
//		// TODO 自动生成的方法存根
//
//	}
//	public void windowDeactivated(WindowEvent e) {
//		// TODO 自动生成的方法存根
//
//	}
//	public void actionPerformed(ActionEvent e) {
//		// TODO 自动生成的方法存根
//		if(e.getSource()==v_close||e.getSource()==v_close1)
//		{
//			player.close();
//			player2.close();
//			this.dispose();
//		}
//	}
//
//}
