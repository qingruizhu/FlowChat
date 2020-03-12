package com.flow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 * @Description:
 * @Auther: qingruizhu
 * @Date: 2020/3/11 01:32
 */
public class Uclient extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new Uclient().start();
    }

    private JButton open = new JButton("选择文件");
    private DatagramPacket datagramPacket;//数据包
    private DatagramSocket datagramSocket;//数据包socket
    private String fileName;//文件名称
    private FileInputStream fis;//文件输入流->读取磁盘文件
    private byte[] buf = new byte[10240];//字节数组->存放读取的文件数据

    public Uclient() {
        this.setSize(150, 100);
        this.setTitle("发送文件");
        this.setLayout(new FlowLayout());
        this.open.addActionListener(this);
        this.add(open);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * 启动socket
     */
    public void start(){
        try {
            datagramSocket = new DatagramSocket(1234);
            System.out.println("socket已启动");
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(open)) {
            //文件选择器组件
            JFileChooser fileChooser = new JFileChooser();
            //显示对话框
            fileChooser.showOpenDialog(this);
            //获取文件的路径和名称
            fileName = fileChooser.getSelectedFile().getPath();
            System.out.println("选择要传送的文件是->【"+fileName+"】");
            try {
                fis = new FileInputStream(fileName);
                int c;
                while ((c = fis.read(buf)) != -1) {
                    //把读到的buf作为数据包发送出去
                    datagramPacket = new DatagramPacket(buf,c,new InetSocketAddress("127.0.0.1", 4567));
                    datagramSocket.send(datagramPacket);
                }
                fis.close();
                System.out.println("发送完毕...");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
