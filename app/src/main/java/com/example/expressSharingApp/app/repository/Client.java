package com.example.expressSharingApp.app.repository;

import android.content.Context;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.HashMap;

public class Client extends Thread{
    private int port = 4000;
    private Socket socket;
    private InputStream ips;
    private HashMap<String , String> messages ;
    private Context context;
    private InetAddress groupOwner ;

    public Client (Context context , InetAddress groupOwner){
        this.context = context;
        this.groupOwner = groupOwner;
    }
    public void run (){
        try{
            socket = new Socket();
            socket.connect(new InetSocketAddress(groupOwner.getHostAddress(), port));
            ips = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(ips);
            messages = (HashMap<String, String>) ois.readObject();
            Toast.makeText(context, messages.toString(), Toast.LENGTH_SHORT).show();
            socket.close();
        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
            Toast.makeText(context, "ERROR in receiving", Toast.LENGTH_SHORT).show();
        }
    }
}
