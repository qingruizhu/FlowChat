package com.flow.server.model;

import com.flow.bgd.model.User;
import com.flow.bgd.service.UserService;
import com.flow.common.Message;
import com.flow.common.MessageType;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * 服务器与某个客户端的通讯线程
 */
public class ServerConClientThread extends Thread{
    private Socket socket;
    private UserService userService;

    public ServerConClientThread(Socket socket) {
        this.socket = socket; //把socket给该线程
    }
    @Override
    public void run() {
        while (true) {
            try {
                /** 1.该线程接收客户端的信息 */
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message)ois.readObject();
                if (message.getMesType().equals(MessageType.message_comm_mes)) {
                    System.out.println(message.getSender()+" 给 "+message.getGetter()+" 说："+message.getCon());
                    /** 2.消息转发 */
                    ServerConClientThread getterThread = ManagerClientThread.get(message.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(getterThread.socket.getOutputStream());
                    oos.writeObject(message);
                } else if (message.getMesType().equals(MessageType.message_login_out)) {
                    /** 3.修改登录状态*/
                    User update = new User();
                    update.setUserId(message.getSender());
                    update.setOnline(0);
                    userService.update(update);
                    //终止线程
                    ObjectOutputStream oos = new ObjectOutputStream(this.socket.getOutputStream());
                    oos.writeObject(message);
                    this.interrupt();
                    ManagerClientThread.remove(message.getSender());
                }else if (message.getMesType().equals(MessageType.message_sendfile)) {
                    /** 发送文件 */
                    ServerConClientThread thread = ManagerClientThread
                            .get(message.getGetter());
                    ObjectOutputStream oos = new ObjectOutputStream(
                            thread.getSocket().getOutputStream());
                    oos.writeObject(message);
                }
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

        }
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
