package com.fakebar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

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
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                String firstLine = reader.readLine();
                if (firstLine == null || firstLine.isEmpty()) {
                    clientSocket.close();
                    continue;
                }
                String[] requestParts = firstLine.split(" ");
                String requestType = requestParts[0]; // Break down first line.
                String requestFile = requestParts[1];
                String httpVersion = requestParts[2];

                HashMap<String, String> headers = new HashMap<>();
                String line;

                while ((line = reader.readLine()) != null && !line.isEmpty()) {

                    String[] parts = line.split(":", 2); // Splitting each line of the request into two parts and placing in a map.
                    if (parts.length == 2) {
                        String headerName = parts[0].trim().toLowerCase();
                        String headerValue = parts[1].trim();
                        headers.put(headerName, headerValue);
                    }
                }

                String responseBody = "<html><head><title>My Server</title></head><body><h1>Hello from my raw socket server!</h1></body></html>"; // Simple placeholder response HTML
                byte[] responseBodyBytes = responseBody.getBytes("UTF-8"); // Response body as bytes in order to send back to client.
                int contentLengthBytes = responseBodyBytes.length;

                writer.write("HTTP/1.1 200 OK\r\n"); // Using the writer to send the response.
                writer.write("Content-Type: text/html; charset=UTF-8\r\n");
                writer.write("Content-Length: " + contentLengthBytes + "\r\n");
                writer.write("\r\n");
                writer.write(responseBody);
                writer.flush();

                clientSocket.close(); // closing client socket.
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
