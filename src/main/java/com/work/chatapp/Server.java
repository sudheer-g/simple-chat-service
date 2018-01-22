package com.work.chatapp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private DataInputStream in =  null;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening on: " + port);
            System.out.println("Waiting for a client ...");
            socket = serverSocket.accept();
            System.out.println("Client Accepted");
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            // reads message from client until "Over" is sent
            String line = "";
            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch(IOException i)
                {
                    throw new RuntimeException(i);
                }
            }
            System.out.println("Closing connection");

            // close connection
            socket.close();
            in.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
