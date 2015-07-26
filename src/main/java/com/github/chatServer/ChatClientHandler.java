package com.github.chatServer;
//クラスライブラリをインポート
import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClientHandler extends Thread{
	private	Socket socket;	// クライアントを表すソケット
	private	BufferedReader in;
	private	BufferedWriter out;
	private	List clients;
	private	String name;

	ChatClientHandler(Socket sock, List clients){//コンストラクタ
		this.socket = sock;
		this.clients = clients;
		this.name = "undefiend" + (clients.size() + 1);
	}

	public String getClientName() {//ゲッターメソッド
		return this.name;
	}
	public void setClientName(String name) {//セッターメソッド
		this.name = name;
	}


	public void run(){
		try{
			open();
			while(true){
				String massage = receive();
				//コマンドを読み取ってそれに応じたクラスを呼び出す。該当しない場合はコメントを送る。
				String[] commands = massage.split(" ");
				if(commands[0].equalsIgnoreCase("bye")){
					CommandBye bye = new CommandBye();
					bye.run(this, clients);
				} else if(commands[0].equalsIgnoreCase("help")){
					CommandHelp help = new CommandHelp();
					help.run(this);
				} else if(commands[0].equalsIgnoreCase("post")){
					CommandPost post = new CommandPost();
					post.run(commands[1], this, clients);
				} else if(commands[0].equalsIgnoreCase("name")){
					CommandName name = new CommandName();
					name.run(commands[1], this, clients);
				} else if(commands[0].equalsIgnoreCase("users")){
					CommandUsers users = new CommandUsers();
					users.run(this, clients);
				} else if(commands[0].equalsIgnoreCase("whoami")){
					CommandWhoami whoami = new CommandWhoami();
					whoami.run(this);
				} else {
					System.out.println("存在しないコマンドです");
				}
			}
		} catch(IOException e){
			e.printStackTrace();//エラーを出す
		} finally{
			close();//サーバーを閉じる
		}
	}

	// クライアントとのデータのやりとりを行うストリームを開くメソッド
	public void open() throws IOException {
		InetAddress address = socket.getInetAddress();
		System.out.println(address);
		InputStream socketIn = socket.getInputStream();
		OutputStream socketOut = socket.getOutputStream();
		in = new BufferedReader(new InputStreamReader(socketIn));
		out = new BufferedWriter(new OutputStreamWriter(socketOut));
	}
	//クライアントからデータを受け取るメソッド
	public String receive() throws IOException{
		return in.readLine();
	}
	// クライアントからデータを送信するメソッド
	public void send(String message) throws IOException{
		System.out.println(message);
		out.write(message);
		out.write("\r\n");
		out.flush();
	}
	// クライアントとの接続を閉じるメソッド
	public void close() {
		//入出力メソッドを閉じる
		if(in != null){ try{ in.close(); } catch(IOException e){ } }
		if(out != null){ try{ out.close(); } catch(IOException e){ } }
		if(socket != null){ try{ socket.close(); } catch(IOException e){ } }
	}
}
