package com.work.chatapp;

import java.io.Console;

public class ClientLauncher {
    public static void main(String[] args) {
        new Client("127.0.0.1", 5000);
    }
}