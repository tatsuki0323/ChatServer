package com.github.chatServer;
//クラスライブラリをインポート
import java.util.*;
import java.io.*;

public class CommandBye {
	public void run(ChatClientHandler client,List clients) throws IOException{
		client.send("接続を切断しました");//接続を切断したことを伝えるメッセージを送信
		clients.remove(client);//clientsからclientを取り除く
		client.close();//サーバとの接続を閉じる
	}
}
