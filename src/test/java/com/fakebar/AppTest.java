package com.fakebar;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 *  Tests for basic running of server and sending a request
 */
public class AppTest {

    private static Thread serverThread; // Separate thread of running tests.

    @BeforeClass // Setting up the thread to run before the tests.
    public static void setUp() throws Exception {
        serverThread = new Thread(() -> {
            App.main(new String[0]); // Start app.java on the newly created thread.
        });
        serverThread.setDaemon(true);
        serverThread.start();

        Thread.sleep(500);
    }

    @AfterClass // Shutting down the threads once the tests end.
    public static void tearDown() throws Exception {
        if (serverThread != null) {
            serverThread.interrupt(); // Shut down the thread at the end.
        }
    }

    @Test
    public void testServerResponse() throws Exception {
        URL url = new URL("http://localhost:8080/"); // Setting up the URL to send a request to.
        HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Open the connection.
        connection.setRequestMethod("GET");
        connection.setDoOutput(true);
        connection.setRequestProperty("User-Agent", "JUnit-Test");
        int responseCode = connection.getResponseCode();
        assertTrue("Expected response code 200, got " + responseCode, responseCode == 200); // get response and compare it
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        assertTrue("Response does not contain expected text.", response.toString().contains("Hello from my raw socket server!"));
    }
}
