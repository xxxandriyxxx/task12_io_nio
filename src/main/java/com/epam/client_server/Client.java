package com.epam.client_server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    private static final int BUFFER_SIZE = 1024;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String message;
        logger("Starting Client...");
        try {
            int port = 9999;
            InetAddress hostIP = InetAddress.getLocalHost();
            InetSocketAddress myAddress =
                    new InetSocketAddress(hostIP, port);
            SocketChannel myClient = SocketChannel.open(myAddress);
            logger(String.format("Trying to connect to %s:%d...",
                    myAddress.getHostName(), myAddress.getPort()));

            do {
                System.out.println("----------------------------------------------");
                System.out.println("Enter message('q' for exit):");
                message = input.nextLine();
                if(message.toUpperCase().equals("Q")){
                    message="*exit*";
                }
                ByteBuffer myBuffer = ByteBuffer.allocate(BUFFER_SIZE);
                myBuffer.put(message.getBytes());
                myBuffer.flip();
                int bytesWritten = myClient.write(myBuffer);
                logger(String
                        .format("Sending Message...: %s\nbytesWritten...: %d",
                                message, bytesWritten));

                //get message from server
                myClient.read(myBuffer);
                String data = new String(myBuffer.array()).trim();
                if (data.length() > 0) {
                    logger(String.format("Message from Server.....: %s\n", data));
                }
            } while(!message.equals("*exit*"));

            logger("Closing Client connection...");
            myClient.close();
        } catch (IOException e) {
            logger(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void logger(String msg) {
        System.out.println(msg);
    }

}
