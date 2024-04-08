package src.main.test.java;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import scr.main.java.Server;

import static org.junit.Assert.assertEquals;

public class ServerTest {
    private Server server;

    @Before
    public void setUp() {
        // Start the server before each test
        server = new Server();
        new Thread(() -> {
            server.main(new String[]{});
        }).start();
    }

    @After
    public void tearDown() {
        // Stop the server after each test
        server.stop();
    }

    @Test
    public void testServerEcho() {
        final String serverAddress = "127.0.0.1"; // Change this to your server's IP address
        final int serverPort = 12345; // Change this to your server's port

        try {
            Socket socket = new Socket(serverAddress, serverPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send a message to the server
            out.println("Hello, Server!");

            // Read the response from the server
            String response = in.readLine();

            // Close the socket
            socket.close();

            // Verify that the server echoed back the message
            assertEquals("Server received: Hello, Server!", response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
