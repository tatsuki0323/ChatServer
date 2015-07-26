package com.github.chatServer;
//クラスライブラリをインポートする
import java.io.*;

public class CommandWhoami {
	public void run(ChatClientHandler client) throws IOException{
		client.send(client.getClientName());//名前を送信する
	}	
}	
