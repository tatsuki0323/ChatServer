package com.github.chatServer;
//クラスライブラリを呼び出す
import java.io.*;
import java.util.*;

public class CommandPost {
	public void run(String message, ChatClientHandler client, List clients) throws IOException {
		List names = new ArrayList();//List型の配列namesの作成
		for(int i = 0; i < clients.size(); i++) {
			ChatClientHandler handler = (ChatClientHandler)clients.get(i);//clientsのi番目の要素をhandlerに追加
			if(handler != client) {//自分自身ではない場合
				names.add(handler.getClientName());//nameに追加
				handler.send("[" + client.getClientName() + "] " + message);//メッセージを送る
			}
			Collections.sort(names);//昇順に並び替える
		}
		if(names.isEmpty()){//送り主外ない場合
			client.send("no one receive message");//送信できないことを伝えるメッセージを送る
		}else {//そうでないなら
			String returnMessage = "";//String型の変数returnMessageを作成
			for(int i = 0; i < names.size(); i++){//nameの数だけ回す
				returnMessage = returnMessage + names.get(i) + ",";//returnMessageに追加
			}
			client.send(returnMessage);//メッセージを返す
		}
	}
}

