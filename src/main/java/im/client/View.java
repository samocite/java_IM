package im.client;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {  // UI界面绘制
    // 聊天区
    private JPanel jpChat;
    private JScrollPane scrollPane;
    private JTextArea taChatList;   // 聊天内容
    private JTextField tfMessage;   // 聊天输入框
    private JButton btnSend;        // 发送按钮

    // 连接服务器区
    private JPanel jpConnect;
    private JLabel jlHost;
    private JLabel jlPort;
    private JTextField tfHost;      // 服务器地址输入框
    private JTextField tfPort;      // 服务器端口输入框
    private JButton btnConnect;     // 连接/断开服务器按钮

    // 用户认证区
    private JPanel jpLogin;
    private JLabel jlNick;
    private JLabel jlPass;
    private JTextField tfName;      // 用户名输入框
    private JPasswordField tfPassword;  // 密码输入框
    private JButton btnLogin;       // 登录按钮
    private JButton btnRegister;    // 注册按钮

    public View() throws HeadlessException {

        jpChat = new JPanel();
        taChatList = new JTextArea(20, 20);
        taChatList.setEditable(false);
        scrollPane = new JScrollPane(taChatList);
        tfMessage = new JTextField(20);
        btnSend = new JButton("发送");

        jpChat.add(tfMessage);
        jpChat.add(btnSend);

        jpConnect = new JPanel();
        jlHost = new JLabel("服务器地址:");
        tfHost = new JTextField(15);
        jlPort = new JLabel("端口号:");
        tfPort = new JTextField(4);
        btnConnect = new JButton("连接");

        jpConnect.add(jlHost);
        jpConnect.add(tfHost);
        jpConnect.add(jlPort);
        jpConnect.add(tfPort);
        jpConnect.add(btnConnect);

        jpLogin = new JPanel();
        jlNick = new JLabel("用户名:");
        tfName = new JTextField(8);
        jlPass = new JLabel("密码:");
        tfPassword = new JPasswordField(8);
        btnLogin = new JButton("登录");
        btnRegister = new JButton("注册");

        jpLogin.add(jlNick);
        jpLogin.add(tfName);
        jpLogin.add(jlPass);
        jpLogin.add(tfPassword);
        jpLogin.add(btnLogin);
        jpLogin.add(btnRegister);

        jpConnect.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        jpLogin.setLayout(new GridLayout(3, 2, 30, 20));
        jpChat.setLayout(new FlowLayout());

        //
        // this.setLayout(new GridBagLayout());
        add(jpConnect, BorderLayout.NORTH);
        add(jpLogin, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(jpChat, BorderLayout.SOUTH);


        setTitle("聊天室");
        setSize(500, 500);
        setLocation(450, 150);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new View();
    }
}
