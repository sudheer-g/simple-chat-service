package com.work.chatapp;

public class ClientLauncher {
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
        client.startClient();
    }
}
