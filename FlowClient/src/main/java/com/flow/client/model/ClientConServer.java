package com.flow.client.model;

import com.flow.common.Message;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientConServer {
    public static Socket socket;
    public boolean sendLoginInfoToServer(Object o){
        boolean b = false;
        try {
            socket = new Socket("127.0.0.1", 9999);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(o);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Message message = (Message)ois.readObject();
            if (message.getMesType().equals("1")) {
                b = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return b;
    }
}
