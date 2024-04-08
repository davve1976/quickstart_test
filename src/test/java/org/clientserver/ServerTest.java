package org.clientserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class ServerTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerTest.class);
    private Server server;

    @Before
    public void setUp() {
        // Start the server before each test
        server = new Server();
        new Thread(() -> {
            Server.main(new String[]{});
        }).start();
    }

    @After
    public void tearDown() {
        // Stop the server after each test
        server.stop();
    }

    @Test
    public void testServerEcho() {
        final int serverPort = 12345; // Change this to your server's port

        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send a message to the server
            out.println("Hello, Server!");

            // Read the response from the server
            String response = in.readLine();

            // Close the socket
            socket.close();

            // Verify that the server echoed back the message
            Assert.assertEquals("Server received: Hello, Server!", response);
        } catch (IOException e) {
            LOGGER.error("Unable to connect to server", e);
        }
    }
}
