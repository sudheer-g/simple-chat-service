package com.work.chatapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private Socket clientSocket;
    private Logger logger = LogManager.getLogger();

    ConnectionHandler(Socket socket) {
        this.clientSocket = socket;
    }

    private void handleChat() {
        try (
                PrintWriter out =
                        new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in =
                        new BufferedReader(
                                new InputStreamReader(clientSocket.getInputStream()));
                BufferedReader stdIn =
                        new BufferedReader(
                                new InputStreamReader(System.in))
        ) {
            String inputLine;
            logger.info("Client Accepted!");
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Client: " + inputLine);
                if (inputLine.equals("Bye.")) {
                    break;
                }
                String input = stdIn.readLine();
                out.println(input);
                if (input.equals("Bye.")) {
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            handleChat();
        }
        catch (Exception e) {
            StackTraceElement elements[] = e.getStackTrace();
            for (StackTraceElement element : elements) {
                logger.warn(element.getMethodName());
            }
            throw new RuntimeException(e);
        }
    }
}
