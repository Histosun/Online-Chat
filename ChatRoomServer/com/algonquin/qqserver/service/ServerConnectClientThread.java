package com.algonquin.qqserver.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

import com.algonquin.qqcommon.Message;
import com.algonquin.qqcommon.MessageType;

public class ServerConnectClientThread extends Thread{
	Socket socket;
	private String userID;
	private boolean loop;
	
	public ServerConnectClientThread(String userID, Socket socket) {
		this.userID=userID;
		this.socket=socket;
		loop=true;
	}
	
	public void run() {
		while(loop) {
			try {
				System.out.println("waiting for client message");
				ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
				Message message=(Message) ois.readObject();
				messageHandler(message);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(userID+" log out");
	}
	
	public void messageHandler(Message message) throws IOException {
		
		if(message.getMesType()==MessageType.MESSAGE_GET_ONLINEUSERS) {
			
			Message retMes=new Message();
			retMes.setMesType(MessageType.MESSAGE_RET_ONLINEUSERS);
			Set<String> onlineUsers=ServerThreadManager.ServerOnlineUsers();
			String retContent="";
			
			for(String userName:onlineUsers) {
				retContent+=onlineUsers;
				retContent+=" ";
			}
			
			retMes.setContent(retContent);
			
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			
			oos.writeObject(retMes);
		}
		else if(message.getMesType()==MessageType.MESSAGE_CLIENT_EXIT) {
			ServerThreadManager.removeOnlineClientThread(userID);
			
			socket.close();
			loop=false;
		}
		else {
			System.out.println("other");
		}
	}
}
