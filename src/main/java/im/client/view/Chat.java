package im.client.view;

import im.client.model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

public class Chat extends JFrame implements ActionListener {

    // 聊天区
    JPanel jpChat = new JPanel();
    // 聊天内容
    JTextArea taChatList = new JTextArea(20, 20);
    JScrollPane spChatList = new JScrollPane(taChatList);
    // 聊天输入框
    JTextArea taMessage = new JTextArea(5, 37);
    JScrollPane spMessage = new JScrollPane(taMessage);
    // 发送按钮
    JButton btnSend = new JButton("发送");
    //建立工具栏
    JToolBar toolBar = new JToolBar();
    // 断开连接按钮
    JButton btnDisconnect = new JButton("断开连接");
    JButton btnChangenick = new JButton("修改昵称");

    NetworkService networkService;

    public Chat(NetworkService cntlogin) throws HeadlessException {
        networkService = cntlogin;  //将从login传来的用户名与socket传入新生成的界面中，使其可以调用原界面生成的socket

        setSize(500, 500);
        setLocation(450, 150);
        init();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void init() {
        setTitle("聊天室");
        taChatList.setEditable(false);
        jpChat.add(spMessage);
        jpChat.add(btnSend);
        jpChat.setLayout(new FlowLayout());

        toolBar.add(btnChangenick);
        toolBar.add(btnDisconnect);
        toolBar.addSeparator();//添加分隔栏

        // 最外层布局
        add(toolBar, BorderLayout.NORTH);
        add(spChatList, BorderLayout.CENTER);
        add(jpChat, BorderLayout.SOUTH);

        btnSend.addActionListener(this);
        btnDisconnect.addActionListener(this);
        btnChangenick.addActionListener(this);

        initNetworkService();
    }

    private void initNetworkService() {
        networkService.setCallback(new Callback() {
            @Override
            public void onConnected(String host, int port) {
                // 连接成功时，弹对话框提示，并将按钮文字改为“断开”
                alert("连接", "成功连接到[" + host + ":" + port + "]");
            }

            @Override
            public void onConnectFailed(String host, int port) {
                // 连接失败时，弹对话框提示，并将按钮文字设为“连接”
                alert("连接", "无法连接到[" + host + ":" + port + "]");
            }

            @Override
            public void onDisconnected() {
                // 断开连接时，弹对话框提示，并将按钮文字设为“连接”
                alert("连接", "连接已断开");
            }

            @Override
            public void onMessageSent(String nick, String message) {
                // 发出消息时，清空消息输入框，并将消息显示在消息区
                taMessage.setText("");
                taChatList.append("我(" + nick + "):\r\n" + message + "\r\n");
            }

            @Override
            public void onMessageReceived(String nick, String message) {
                // 收到消息时，将消息显示在消息区
                taChatList.append(nick + ":\r\n" + message + "\r\n");
            }
        });
    }

    private void alert(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnSend) {
            // 发送信息
            String message = taMessage.getText();
            //alert("test", message);
            networkService.sendmessage(message);
        } else if (actionEvent.getSource() == btnDisconnect) {
            networkService.disconnect();
            this.setVisible(false);
            new Login();
        } else if (actionEvent.getSource() == btnChangenick) {
            String new_nick = JOptionPane.showInputDialog("请输入用户名");
            networkService.changenick(new_nick);
        }
    }
}
