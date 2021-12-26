package im.client.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import im.client.model.*;

public class Login extends JFrame implements ActionListener {
    // 连接服务器
    JPanel jpConnect = new JPanel();
    // 服务器地址输入框
    JLabel jlHost = new JLabel("服务器地址:");
    JTextField tfHost = new JTextField(15);
    // 服务器端口输入框
    JLabel jlPort = new JLabel("端口号:");
    JTextField tfPort = new JTextField(4);
    // 用户名输入框
    JLabel jlNick = new JLabel("用户名:");
    JTextField tfNick = new JTextField(8);
    // 连接/断开服务器按钮
    JButton btnConnect = new JButton("连接");

    NetworkService networkService = new NetworkService();

    public Login() throws HeadlessException {
        setLocation(500, 150);
        setSize(500, 120);
        init();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void init() {
        setTitle("连接");
        jpConnect.add(jlHost);
        jpConnect.add(tfHost);
        jpConnect.add(jlPort);
        jpConnect.add(tfPort);
        jpConnect.add(jlNick);
        jpConnect.add(tfNick);

        btnConnect.addActionListener(this);

        jpConnect.setLayout(new GridLayout(3, 1, 30, 0));
        add(jpConnect, BorderLayout.CENTER);
        add(btnConnect, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btnConnect) {
            try {
                networkService.connect(tfHost.getText(), Integer.parseInt(tfPort.getText()), tfNick.getText(), networkService);
                this.setVisible(false);
            } catch (Exception e) {
                JOptionPane.showConfirmDialog(
                        this, "不能连接到指定的服务器。\n请确认连接设置是否正确。", "提示",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            }

        }
    }
}
