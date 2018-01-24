package com.work.chatapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
    private Logger logger = LogManager.getLogger();
    private final int portNumber;
    Server(int portNumber) {
        this.portNumber = portNumber;
    }

    private void acceptClients(ServerSocket serverSocket) throws IOException{
        while(true) {
            Socket clientSocket = serverSocket.accept();
            Runnable connectionHandler = new ConnectionHandler(clientSocket);
            new Thread(connectionHandler).start();
        }
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            acceptClients(serverSocket);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
