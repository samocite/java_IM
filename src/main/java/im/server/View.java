package im.server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.net.*;

public class View implements SimpleChatService.OnSocketAcceptListener {
    // 服务端管理界面，因为没有其他界面和图形化，使用单独一个文件
    ServerSocket serverSocket;
    Socket socket;
    SimpleChatService service = null;
    int Port = 60001;

    Scanner scn = new Scanner(System.in);
    public View(SimpleChatService service) {
        this.service = service;
        this.service.setOnAcceptListener(this);
        init();
    }

    public void init() {
        System.out.println(
                """
                        ======================
                              服务端管理工具
                        ======================
                        1) 启动服务 | 2) 关闭服务
                        3) 用户管理 | 4) 消息管理
                        5) 端口管理 | 6) 关闭工具
                        ======================
                        """);
        while (true) {
            System.out.println("输入数字使用对应功能> ");
            switch (scn.nextInt()) {
                case 1: serviceStart(); break;
                case 2: serviceStop(); break;
                case 3: manageUser(); break;
                case 4: manageMessage(); break;
                case 5: managePort(); break;
                case 6: break;
                default:
                    throw new IllegalStateException("无效值\n");
            }
        }
    }
    public void serviceStart() {
        System.out.println("服务启动中...\n");
        service.startup(Port);
        System.out.println("服务启动成功\n");
    }
    public void serviceStop() {
        System.out.println("服务关闭中...\n");
        service.shutdown();
        System.out.println("服务已关闭...\n");
    }
    public void manageUser() {

    }
    public void manageMessage() {

    }
    public void managePort() {
        System.out.println("当前端口为" + Port);
        System.out.println("设置服务的端口:");
        Port = scn.nextInt();
    }

    @Override
    public void onSocketAccept(Socket socket) {
        System.out.println("客户端" + socket.getInetAddress() + "已连接\r\n");
    }
}
