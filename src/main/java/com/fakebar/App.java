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

                String firstLine = reader.readLine();
                String requestType = firstLine.split(" ")[0]; // Break down first line.
                String requestFile = firstLine.split(" ")[1];
                String httpVersion = firstLine.split(" ")[2];

                String secondLine = reader.readLine();
                String hostName = secondLine.split(" ")[1];

                String thirdLine = reader.readLine();
                String userAgent = thirdLine.split(" ")[1];

                String fourthLine = reader.readLine();
                String accept = fourthLine.split(" ")[1];

                String fifthLine = reader.readLine();
                String contentType = fifthLine.split(" ")[1];

                String sixthLine = reader.readLine();
                String contentLength = sixthLine.split(" ")[1];

                output.println("Response placeholder");

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
