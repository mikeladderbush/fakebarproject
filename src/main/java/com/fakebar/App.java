package com.fakebar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                handleClient(clientSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try {
            InputStream input = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
            }

            String htmlResponse = "<html><body><h1>Hello</h1></body></html>";
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: " + htmlResponse.getBytes().length + "\r\n" +
                    "\r\n" +
                    htmlResponse;

            output.write(httpResponse.getBytes("UTF-8"));
            output.flush();

            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
