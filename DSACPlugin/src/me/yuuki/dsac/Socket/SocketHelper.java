package me.yuuki.dsac.Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import me.yuuki.dsac.Main;

public class SocketHelper extends Thread{
	
	ServerSocket socket = Main.server;
	public void run(){
		try {
		    while(!Thread.currentThread().isInterrupted()){
				Socket sock = null;
				sock = socket.accept();
				BufferedReader br = null;
				br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				String str = null;
				str = br.readLine();
				if(str!=null && str.length()>0) {
					Main.playerHelper.check(str);
				}
			} 
		} catch (IOException e) {
		    
		}
	}

}
