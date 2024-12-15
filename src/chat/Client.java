package src.chat;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
    private Socket socket;  //to establish a connection
    private BufferedReader bufferedReader;   //to read messages sent from client
    private BufferedWriter bufferedWriter;   //to send messages to client
    private String userName;

    //parametrized constructor
    public Client(Socket socket, String userName) {
        try {
            this.socket = socket;
            //to convert from byte stream(getOutputStream) to char stream(OutputStreamWriter)
            //casting only for efficiency
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //exception
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));   //exception
            this.userName = userName;
        }
        catch (IOException e){
            closeAll(socket, bufferedWriter, bufferedReader);
            System.err.println("Error establishing client connection!");
        }
    }

    public void sendMessage(){
        try {
            bufferedWriter.write(userName);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner input = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageTosend = input.nextLine();
                bufferedWriter.write(userName + ": ");
                bufferedWriter.write(messageTosend);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }
        catch (IOException e){
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
            String ip = "127.0.0.1";
            int port = 2024;
            Socket socket = new Socket(ip, port);

            Scanner input = new Scanner(System.in);
            System.out.print("Enter your username: ");
            String username = input.nextLine();

            Client client = new Client(socket, username);

            client.takeMessage();
            client.sendMessage();
        }
        catch (ConnectException e) {
            System.err.println("Connection Failed: " + e.getMessage());
            // Detailed error handling
            e.printStackTrace();
        }
    }
}
