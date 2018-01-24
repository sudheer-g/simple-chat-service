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

    private Socket acceptClients(ServerSocket serverSocket) throws IOException{
        return serverSocket.accept();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = acceptClients(serverSocket);
            Runnable connectionHandler = new ConnectionHandler(clientSocket);
            new Thread(connectionHandler).start();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
