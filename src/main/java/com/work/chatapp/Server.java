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
        PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader stdIn = null;
        try {
            ServerSocket serverSocket =
                    new ServerSocket(portNumber);
            Socket clientSocket = acceptClients(serverSocket);
            out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            stdIn = new BufferedReader(
                    new InputStreamReader(System.in));
            String inputLine;
            logger.info("Client Accepted!");
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
        finally {
            if(out!= null) {
                out.close();
            }
            if(in!= null) {
                try {
                    in.close();
                }
                catch (IOException e) {
                    logger.warn(e);
                }
            }
            if(stdIn!= null) {
                try {
                    stdIn.close();
                }
                catch (IOException e) {
                    logger.warn(e);
                }
            }
        }

    }
}
