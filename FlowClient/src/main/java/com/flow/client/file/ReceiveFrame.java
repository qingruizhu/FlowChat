package com.flow.client.file;


import com.flow.common.Message;
import com.flow.common.MessageType;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class ReceiveFrame extends JFrame implements ActionListener {

    JButton save;
    File file;
    FileWriter filewrite;
    BufferedWriter buffwrite;
    InputStream in;
    DataOutputStream dataout;
    JFileChooser fileDialog;
    Message ms = new Message();
    int flag = 0;
    DataOutputStream fos;
    FileOutputStream fileoutputstream;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //QqChat qqChat=new QqChat("1","s");
        //ReceveFrame r=new ReceveFrame();
        //r.setVisible(true);
    }

    public ReceiveFrame() {
        save = new JButton("Áí´æÎª");
        save.addActionListener(this);
        this.add(save);
        this.setBounds(1100, 700, 100, 30);
        this.setUndecorated(true);
		/*file=new File("D:\\1.txt");
		try {
			filewrite = new FileWriter(file);
			buffwrite=new BufferedWriter(filewrite);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	*/
    }

    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        if (arg0.getSource() == save) {
            if (flag == 0) {
                fileDialog = new JFileChooser();
                FileNameExtensionFilter fnef = new FileNameExtensionFilter("TXT and INI Files (.txt, .ini)", "txt", "ini");
                FileNameExtensionFilter txt = new FileNameExtensionFilter("TXT Files (.txt)", "txt");
                FileNameExtensionFilter ini = new FileNameExtensionFilter("INI Files (.ini)", "ini");
                fileDialog.addChoosableFileFilter(fnef);
                fileDialog.addChoosableFileFilter(ini);
                fileDialog.addChoosableFileFilter(txt);
                //fileDialog.setDialogType(JFileChooser.SAVE_DIALOG);
                fileDialog.showSaveDialog(this);
                file = fileDialog.getSelectedFile();
                fileDialog.getCurrentDirectory();
                file.getParent();
                file = new File(file.getParent() + file.getName() + "." + ms.getFileType());

                try {
                    fos = new DataOutputStream(new FileOutputStream(file.getPath()));
                    byte[] by = ms.getFileByte();
                    fos.write(by);
                    fos.close();
                    //filewrite = new FileWriter(file);
                    //buffwrite=new BufferedWriter(filewrite);
                    //dataout=new DataOutputStream(new FileOutputStream(file.getPath()));
                    //dataout.writeUTF(ms.getCol());
                    //dataout.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            saveFile();
            this.setVisible(false);
        }

    }

    public void setMs(Message m) {
        ms = m;
    }

    public void saveFile() {
        if (ms.getMesType().equals(MessageType.message_sendfile)) {
            try {
                fos.close();
                // buffwrite.write(ms.getCon());
                // buffwrite.close();
                //filewrite.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
