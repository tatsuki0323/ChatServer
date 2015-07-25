package com.github.chatServer;

import java.net.*;
import java.io.*;

public class ChatServer{
    private ServerSocket server;
    public void listen(){
        try{
            server = new ServerSocket(18080);
            System.out.println("Echoサーバをポート18080で起動しました．");
          
            while(true){
                Socket socket = server.accept();
		    System.out.println("クライアントが接続してきました．");
                ChatClientHandler handler = new ChatClientHandler(socket);
                handler.start();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        ChatServer echo = new ChatServer();
        echo.listen();
    }
}
