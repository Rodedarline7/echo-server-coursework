import java.io.*;
import java.net.*;

public class EchoServer {

    public static void main(String[] args) {
        int port = 8081;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Echo server listening on port " + port + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                Thread clientThread = new Thread(new ClientHandler(clientSocket));
                clientThread.start();
            }

        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        String clientAddress = clientSocket.getInetAddress().getHostAddress();
        System.out.println("Connected to " + clientAddress);

        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            String data = in.readLine();
            System.out.println("Received from " + clientAddress + ": " + data);
            out.write(data + "\n");
            out.flush();
        } catch (IOException e) {
            System.err.println("Error handling client " + clientAddress + ": " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
                System.out.println("Connection with " + clientAddress + " closed.");
            } catch (IOException e) {
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
