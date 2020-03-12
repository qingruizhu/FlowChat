package com.flow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @Description:
 * @Auther: qingruizhu
 * @Date: 2020/3/11 15:21
 */
public class UServer extends JFrame implements ActionListener {
    public static void main(String[] args) {
        new UServer().start();
    }

    private JButton save = new JButton("保存文件");
    private DatagramPacket datagramPacket;
    private DatagramSocket datagramSocket;
    private FileOutputStream fos;
    private FileInputStream fis;
    private String filename;
    private byte[] buf = new byte[10240];

    public UServer() {
        this.setSize(150,100);
        this.setTitle("接受文件");
        this.setLayout(new FlowLayout());
        this.add(save);
        save.addActionListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    public void start(){
        try {
            datagramSocket = new DatagramSocket(4567);
            System.out.println("服务端开始接收文件->port="+4567);
            fos = new FileOutputStream("/Users/qingruizhu/Desktop/test/temp.dat");
            while (true) {
                datagramPacket = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(datagramPacket);
                fos.write(datagramPacket.getData());
                fos.flush();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(save)) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showSaveDialog(this);
            filename = fileChooser.getSelectedFile().getPath();
            try {
                fos.close();
                fos = new FileOutputStream(filename);
                System.out.println("服务端保存文件路径->【"+filename+"】");
                fis = new FileInputStream("/Users/qingruizhu/Desktop/test/temp.dat");
                int c;
                while ((c = fis.read(buf))!=-1) {
                    fos.write(buf);
                    fos.flush();
                }
                fis.close();
                fos.close();
                System.out.println("接收完毕。");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
