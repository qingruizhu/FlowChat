package com.qq.client.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.File;

public class SaveImage {

	public static int doSave(BufferedImage img){
		try{
			JFileChooser chooser=new JFileChooser();
			//��ӹ�����
			FileNameExtensionFilter jpgFilter=new FileNameExtensionFilter("JPG/JPEG - JPG�ļ�","jpg");
			FileNameExtensionFilter gifFilter=new FileNameExtensionFilter("GIF - Compuserve GIF","gif");
			chooser.addChoosableFileFilter(gifFilter);
			chooser.addChoosableFileFilter(jpgFilter);
			//�򿪱���Ի���
			int result=chooser.showSaveDialog(null);
			if(result==JFileChooser.APPROVE_OPTION){//����
				File file=chooser.getSelectedFile();
				String about="jpg";//�ļ�����
				String pathname=file.toString().toLowerCase();
				FileNameExtensionFilter filter=(FileNameExtensionFilter )chooser.getFileFilter();
				String ext=filter.getExtensions()[0];//�ļ���׺��
				if("jpg".equals(ext)){
					if(!pathname.endsWith(".jpg")){
						pathname+=".jpg";
						file=new File(pathname);
						about="jpg";
					}
				}else if("gif".equals(ext)){
					if(!pathname.endsWith(".gif")){
						pathname+=".gif";
						file=new File(pathname);
						about="gif";
					}
				}
				
				if(ImageIO.write(img, about, file)){
					JOptionPane.showMessageDialog(null, "����ɹ�");
					return 1;
				}else{
					JOptionPane.showMessageDialog(null, "����ʧ��!");
					return 0;
				}
			}
		}catch(Exception e){
		}
		return 0;
	}
}

