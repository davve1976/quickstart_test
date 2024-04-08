import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        final String serverAddress = "127.0.0.1"; // Change this to your server's IP address
        final int serverPort = 12345; // Change this to your server's port

        try {
            Socket socket = new Socket(serverAddress, serverPort);

            // Open streams for reading from and writing to the socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send data to the server
            out.println("Hello, Server!");

            // Read data from the server
            String response = in.readLine();
            System.out.println("Server response: " + response);

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
