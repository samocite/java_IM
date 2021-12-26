package im.client.model;

import javax.swing.*;
import java.net.*;
import java.io.*;

import im.client.view.*;

public class NetworkService {
    Callback callback;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    // 套接字对象
    Socket connectSocket;
    // 套接字输入流对象，读取收到的消息
    DataInputStream inputStream;
    // 套接字输出流对象，发送聊天消息
    DataOutputStream outputStream;
    // 当前连接状态
    boolean isConnected = false;
    // 当前昵称
    String nick;

    public void connect(String host, int port, String nick, NetworkService cntlogin) throws IOException {
        connectSocket = new Socket(host, port);
        this.nick = nick;
        isConnected = true;
        // 通知外界已连接
        new Chat(cntlogin);
        if (callback != null) {
            callback.onConnected(host, port);
        }
        // 开始侦听是否有聊天消息到来
        beginListening();
    }

    private void beginListening() {
        Runnable listening = () -> {
            try {
                inputStream = new DataInputStream(connectSocket.getInputStream());

                while (true) {
                    String[] s = inputStream.readUTF().split("#");
                    if (callback != null) {
                        callback.onMessageReceived(s[0], s[1]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        (new Thread(listening)).start();
    }

    public void disconnect() {
        try {
            if (connectSocket != null) {
                connectSocket.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            isConnected = false;
            // 通知外界连接断开
            if (callback != null) {
                callback.onDisconnected();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendmessage(String message) {
        if (!connectSocket.isClosed()) {
            if (nick == null || "".equals(nick) || message == null || "".equals(message)) {
                return;
            }
            try {
                outputStream = new DataOutputStream(connectSocket.getOutputStream());
                outputStream.writeUTF(nick + "#" + message);
                outputStream.flush();
                if (callback != null) {
                    callback.onMessageSent(nick, message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void changenick(String nick) {
        this.nick = nick;
    }
}
