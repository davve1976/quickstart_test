package org.clientserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(Client.class);
    public static void main(String[] args) {
        final int serverPort = 12345; // Change this to your server's port

        try (Socket socket = new Socket(InetAddress.getLocalHost(), serverPort)) {
            // Open streams for reading from and writing to the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send data to the server
            out.println("Hello, Server!");

            // Read data from the server
            String response = in.readLine();
            System.out.println("Server response: " + response);

        } catch (IOException e) {
            LOGGER.error("Unable to connect to server", e);
        }
    }
}
