package com.github.chatServer;
//クラスライブラリをインポート
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private ServerSocket server;//ServerSoket型の変数serverを宣言
    private List clients = new ArrayList();//List型の変数clientsを作成
    public void listen() {
	try{
	    // ToDo: ポート番号を最後に戻す
	    server = new ServerSocket(18080);//サーバをポート18080で開く
 	    System.out.println("chatサーバをポート18080で起動しました。");
	    while(true){
		Socket socket = server.accept();//Socket型の変数socketを作成
		ChatClientHandler handler = new ChatClientHandler(socket, clients);//ChatClientHandler型の変数handlerに追加する
		clients.add(handler);//clientsにhandlerを追加する
		System.out.println("クライアントが接続してきました。");
		handler.start();
	    }
	} catch(IOException e){
	    e.printStackTrace();//エラーを出す
	}
    }
    public static void main(String[] args) {
	ChatServer chat = new ChatServer();//オブジェクトchatの作成
	chat.listen();//chatのlistenを呼び出す
    }
}
