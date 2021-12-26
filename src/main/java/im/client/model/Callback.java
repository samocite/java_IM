package im.client.model;

public interface Callback {
    void onConnected(String host, int port);        //连接成功
    void onConnectFailed(String host, int port);    //连接失败
    void onDisconnected();                          //已经断开连接
    void onMessageSent(String name, String msg);    //消息已经发出
    void onMessageReceived(String name, String msg);//收到消息
}
