package com.work.chatapp;
import java.io.IOException;


public class ServerLauncher {
    public static void main(String[] args) throws IOException{
        new Server(5000);
    }
}
