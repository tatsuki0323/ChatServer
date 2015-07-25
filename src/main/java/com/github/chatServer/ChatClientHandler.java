package com.github.chatServer;

import java.io.*;
import java.net.*;

public class ChatClientHandler extends Thread{
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public ChatClientHandler(Socket socket){
        this.socket = socket;
    }

    /**
     * ����¹Ԥ�Ԥ����˻��व���᥽�å�
     */
     
    public void run(){
        try{
            open();
            while(true){
                String message = receive();
                if(message.equals("")) break;
                send(message);
            }
        } catch(IOException e){
            e.printStackTrace();
        } finally{
            close();
        }
    }

    /**
     * ���饤����ȤȤΥǡ����Τ�����Ԥ����ȥ꡼��򳫤��᥽�å�
     */
    public void open() throws IOException{
        in = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );
        out = new BufferedWriter(
            new OutputStreamWriter(socket.getOutputStream())
        );
    }
    /**
     * ���饤����Ȥ���ǡ�����������᥽�å�
     */
    public String receive() throws IOException{
        String line = in.readLine();
        System.out.println(line);
        return line;
    }

    /**
     * ���饤����Ȥ˥ǡ�������������᥽�å�
     */   
    public void send(String message) throws IOException{
        out.write(message);
        out.write("\r\n");
        out.flush();
    }

    /**
     * ���饤����ȤȤ���³���Ĥ���᥽�å�
     */
    public void close(){
        if(in != null){
            try{
                in.close();
            } catch(IOException e){ }
        }
        if(out != null){
            try{
                out.close();
            } catch(IOException e){ }
        }
        if(socket != null){
            try{
                socket.close();
            } catch(IOException e){ }
        }
    }
}
