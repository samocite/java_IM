package im.server;

import im.client.view.Login;

public class ServerStart {
    public static void main(String[] args) {
        ClientManager clientManager = new ClientManager();
        SimpleChatService service = new SimpleChatService(clientManager);
        View view = new View(service);
    }
}
