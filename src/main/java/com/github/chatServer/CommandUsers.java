/*
 今接続しているクライアントの名前を返す
 */
package com.github.chatServer;
//クラスライブラリをインポートする
import java.io.*;
import java.util.*;

public class CommandUsers {
	public void run(ChatClientHandler client,List clients) throws IOException{
		String returnName = "";//String型の変数returnNameを作成
		for(int i = 0; i < clients.size(); i++){
			ChatClientHandler handler = (ChatClientHandler)clients.get(i);//clientsのi番目の要素をhandlerに追加
			returnName = returnName + handler.getClientName() + ",";//returnNameに追加
		}
		client.send(returnName);//メッセージを送る
	}
}
