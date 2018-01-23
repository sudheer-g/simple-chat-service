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

    @Override
    public void run() {
        try (
                ServerSocket serverSocket =
                        new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(
                        new InputStreamReader(System.in));

        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client: " + inputLine);
                if (inputLine.equals("Bye.")) {
                    break;
                }
                String input = stdIn.readLine();
                out.println(input);
                if(input.equals("Bye.")) {
                    break;
                }
            }
        } catch (IOException e) {
            StackTraceElement elements[] = e.getStackTrace();
            for (StackTraceElement element : elements) {
                logger.warn(element.getMethodName());
            }
            throw new RuntimeException(e);
        }

    }
}
