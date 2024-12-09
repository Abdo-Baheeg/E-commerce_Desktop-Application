package src.chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345; // Define a port for the server
    private static Map<String, PrintWriter> clients = new HashMap<>(); // Store connected clients

    public static void main(String[] args) {
        System.out.println("Chat Server started...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                // Accept a new client
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Handle each client in a separate thread
    private static class ClientHandler extends Thread {
        private Socket socket;
        private String clientName;
        private PrintWriter out;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
            ) {
                // Save the client's output stream for broadcasting
                this.out = out;

                // Register the client
                out.println("Enter your name:");
                clientName = in.readLine();
                synchronized (clients) {
                    clients.put(clientName, out);
                }
                System.out.println(clientName + " connected.");

                // Broadcast messages
                String message;
                while ((message = in.readLine()) != null) {
                    broadcast(clientName, message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Remove the client on disconnect
                synchronized (clients) {
                    clients.remove(clientName);
                }
                System.out.println(clientName + " disconnected.");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Broadcast a message to all connected clients
        private void broadcast(String sender, String message) {
            synchronized (clients) {
                for (Map.Entry<String, PrintWriter> client : clients.entrySet()) {
                    if (!client.getKey().equals(sender)) { // Avoid sending to the sender
                        client.getValue().println(sender + ": " + message);
                    }
                }
            }
        }
    }
}

