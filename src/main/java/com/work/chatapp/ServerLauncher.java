package com.work.chatapp;
import java.io.IOException;


public class ServerLauncher {
    public static void main(String[] args) throws IOException{
        final int portNumber = 5000;
        Thread server = new Thread(new Server(portNumber));
        server.start();
    }
}
