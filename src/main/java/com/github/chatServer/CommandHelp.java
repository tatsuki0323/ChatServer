package com.github.chatServer;
//クラスライブラリをインポートする
import java.io.*;

public class CommandHelp{
	public void run(ChatClientHandler client) throws IOException{
		client.send("BYE,HELP,NAME,POST,USERS,WHOAMI");	//今使えるコマンドを送信
	}			
}			
