import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 8081;

        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String message = "Hello from EchoClient!";
            System.out.println("Sending: " + message);
            out.write(message + "\n");
            out.flush();

            String response = in.readLine();
            System.out.println("Received: " + response);

        } catch (IOException e) {
            System.err.println("Client error: " + e.getMessage());
        }
    }
}
