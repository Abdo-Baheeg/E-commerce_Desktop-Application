package src.chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocServer {
    public static void main(String[] args) throws Exception {
        System.out.println("Server started!");
        try(ServerSocket ss = new ServerSocket(2024)) {
            System.out.println("Server is waiting for client request?");
            Socket s = ss.accept();
            System.out.println("Client connected!");

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));  //to fetch the data
            System.out.println("Client data: " + br.readLine());
        }
    }
}
