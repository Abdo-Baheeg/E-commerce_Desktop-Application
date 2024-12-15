package src.chat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private Socket socket;  //to establish a connection
    private BufferedReader bufferedReader;   //to read messages sent from client
    private BufferedWriter bufferedWriter;   //to send messages to client

    //parametrized constructor
    public Server(ServerSocket serverSocket) {
        try {
            this.serverSocket = serverSocket;
            this.socket = this.serverSocket.accept();
            System.out.print("Client connected! ");
            //to convert from byte stream(getOutputStream) to char stream(OutputStreamWriter)
            //casting only for efficiency
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //exception
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));   //exception
        }
        catch (IOException e){
            System.err.println("Error establishing client connection" + e.getMessage());
            closeAll(socket, bufferedWriter, bufferedReader);
        }
    }

    public void sendMessage(){
        try {
            Scanner input = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageTosend = input.nextLine();
                bufferedWriter.write("Admin" + ": ");
                bufferedWriter.write(messageTosend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
        catch (IOException e){
            System.err.println("Error sending message: " + e.getMessage());
            closeAll(socket, bufferedWriter, bufferedReader);
        }
    }

    public void takeMessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String messageFromchat;

                while(socket.isConnected()){
                    try{
                        messageFromchat = bufferedReader.readLine();
                        System.out.println(messageFromchat);
                    }
                    catch (IOException e){
                        System.err.println("Error receiving message: " + e.getMessage());
                        closeAll(socket, bufferedWriter, bufferedReader);
                    }
                }
            }
        }).start();
    }

    public void closeAll(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader){
        try{
            if (bufferedReader != null)
                bufferedReader.close();
            if (bufferedWriter != null)
                bufferedWriter.close();
            if (socket != null)
                socket.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        try {
            int port = 2024;
            ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Server is started!");
            System.out.println("Waiting for client connection!");

            Server server = new Server(serverSocket);
            server.takeMessage();
            server.sendMessage();
        }
        catch (IOException e) {
            System.err.println("Server startup error: " + e.getMessage());
            e.printStackTrace();
        }

    }
}
