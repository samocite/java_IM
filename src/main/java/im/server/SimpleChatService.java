package im.server;

import javax.sound.sampled.Port;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleChatService {
    public interface OnSocketAcceptListener {
        void onSocketAccept(Socket socket);
    }

    private ClientManager clientManager = null;
    private OnSocketAcceptListener onAcceptListener = null;
    private ServerSocket serverSocket = null;
    private int Port;

    public SimpleChatService(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public void setOnAcceptListener(OnSocketAcceptListener listener) {
        onAcceptListener = listener;
    }

    public void startup(int Port) {
        // TODO Auto-generated method stub
        this.Port = Port;
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                runStartup();
            }

        });
        thread.start();
    }

    public void shutdown() {
        try {
            clientManager.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runStartup() {
        try {
            serverSocket = new ServerSocket(Port);
            while (true) {
                Socket socket = serverSocket.accept();
                if (onAcceptListener != null) {
                    onAcceptListener.onSocketAccept(socket);
                }

                clientManager.addClientSocket(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
