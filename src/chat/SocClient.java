package src.chat;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class SocClient {

    public static void main(String[] args) throws Exception{
        String ip = "localhost";
        int port = 2024;

        //to send a request to the server
        try(Socket s = new Socket(ip, port)) { //throws an exception
            System.out.println("Connected successfully");
//          System.out.println("chat with admin");
//          Scanner input = new Scanner(System.in);
//          String i1 = input.next();
            String i1 = "I am happy!!";

            //to make the server do something for client
            OutputStreamWriter os = new OutputStreamWriter(s.getOutputStream());   //converting it to ostream format
            PrintWriter out = new PrintWriter(os);   //sending data to server
            out.println(i1);
            os.flush();  //to force it to send data
        }
    }

}
