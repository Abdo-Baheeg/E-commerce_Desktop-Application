//package src.chat;
//
//import java.io.*;
//import java.net.ConnectException;
//import java.net.Socket;
//import java.util.Scanner;
//
//public class Client
//{
//    private Socket socket;  //to establish a connection
//    private BufferedReader bufferedReader;   //to read messages sent
//    private BufferedWriter bufferedWriter;   //to send messages
//    private String userName;
//
//    //parametrized constructor
//    public Client(Socket socket, String userName) {
//        try {
//            this.socket = socket;   //exception
//            //to convert from byte stream(getOutputStream) to char stream(OutputStreamWriter)
//            //casting only for efficiency
//            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); //exception
//            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));   //exception
//            this.userName = userName;
//        }
//        catch (IOException e){
//            System.err.println("Error establishing client connection " + e.getMessage());
//            closeAll(socket, bufferedWriter, bufferedReader);
//        }
//    }
//
//    public void sendMessage(){
//        try {
//            bufferedWriter.write(userName);
//            bufferedWriter.newLine();
//            bufferedWriter.flush();
//
//            Scanner input = new Scanner(System.in);
//            while (socket.isConnected()) {
//                String messageTosend = input.nextLine();
//                bufferedWriter.write(userName + ": ");
//                bufferedWriter.write(messageTosend);
//                bufferedWriter.newLine();
//                bufferedWriter.flush();
//            }
//        }
//        catch (IOException e){
//            System.err.println("Error sending messages " + e.getMessage());
//            closeAll(socket, bufferedWriter, bufferedReader);
//        }
//    }
//
//    public void takeMessage(){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String messageFromchat;
//
//                while(socket.isConnected()){
//                    try{
//                        messageFromchat = bufferedReader.readLine();
//                        System.out.println(messageFromchat);
//                    }
//                    catch (IOException e){
//                        System.err.println("Error receiving messages " + e.getMessage());
//                        closeAll(socket, bufferedWriter, bufferedReader);
//                    }
//                }
//            }
//        }).start();
//    }
//
//    public void closeAll(Socket socket, BufferedWriter bufferedWriter, BufferedReader bufferedReader){
//        try{
//            if (bufferedReader != null)
//                bufferedReader.close();
//            if (bufferedWriter != null)
//                bufferedWriter.close();
//            if (socket != null)
//                socket.close();
//        }
//        catch (IOException e) {
//            System.err.println("Error in closing " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//
//    public static void main(String[] args) throws IOException {
//        try {
//            String ip = "192.168.1.3";
//            int port = 2024;
//            Socket socket = new Socket(ip, port);
//
//            Scanner input = new Scanner(System.in);
//            System.out.print("Enter your username: ");
//            String username = input.nextLine();
//
//            Client client = new Client(socket, username);
//            client.takeMessage();
//            client.sendMessage();
//        }
//        catch (ConnectException e) {
//            System.err.println("Client startup error: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}
