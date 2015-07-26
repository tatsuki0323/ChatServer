package com.github.chatServer;
//クラスライブラリをインポート
import java.io.*;
import java.util.*;

public class CommandName {
	public void run(String name, ChatClientHandler client, List clients) throws IOException{
		for(int i = 0; i < clients.size(); i++) {//クライアントの人数だけ回す
			ChatClientHandler handler = (ChatClientHandler)clients.get(i);
			if(handler.getClientName().equals(name)) {//もし他のクライアントが名前を使用していた場合
				client.send("この名前は他の人が使用しています。");//メッセージを送る
				return;
			}
		}
			client.send("名前を["+name+"]に変更しました");//名前を変更したことを知らせるメッセージを送る
			client.setClientName(name);//setClientNameを呼び出す。
	}
}
