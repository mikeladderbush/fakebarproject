package com.fakebar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * https://docs.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080); // Creates a new socket that listens on the given port.
            while (true) {
                Socket clientSocket = serverSocket.accept(); // ServerSocket.accept() returns a new client socket that
                                                             // will be connected to the server.
                clientSocket.getInputStream(); //
                PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine, outputLine;

                while ((inputLine = reader.readLine()) != null) {
                    inputLine = processInput(inputLine);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String processInput(String inputLine) {

        String processedInput = inputLine;

        if (inputLine.contains("HTTP")) {

        } else if (inputLine.contains("Host")) {

        } else if (inputLine.contains("{")) {

        } else {
            processedInput = "";
        }
        return processedInput;
    }
}
