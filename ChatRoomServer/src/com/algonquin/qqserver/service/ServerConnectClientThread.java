package com.algonquin.qqserver.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Set;

import com.algonquin.qqcommon.Message;
import com.algonquin.qqcommon.MessageType;

public class ServerConnectClientThread extends Thread{
	private Socket socket;
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
		System.out.println(message.getMesType());
		if(message.getMesType()==MessageType.MESSAGE_GET_ONLINEUSERS) {
			
			Message retMes=new Message();
			retMes.setMesType(MessageType.MESSAGE_RET_ONLINEUSERS);
			Set<String> onlineUsers=ServerThreadManager.serverOnlineUsers();
			String retContent="";
			
			for(String userName:onlineUsers) {
				retContent+=onlineUsers;
				retContent+=" ";
			}
			
			retMes.setContent(retContent);
			
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			
			oos.writeObject(retMes);
		} else if(message.getMesType()==MessageType.MESSAGE_CLIENT_EXIT) {
			ServerThreadManager.removeOnlineClientThread(userID);
			
			socket.close();
			loop=false;
		} else if(message.getMesType()==MessageType.MESSAGE_COMM_MES) {
			String receriverID=message.getReceiver();
			ServerConnectClientThread thread=ServerThreadManager.getClientThreadByID(receriverID);
			System.out.println(message.getSender()+" send to "+message.getReceiver());
			if(thread!=null) {
				ObjectOutputStream oos = new ObjectOutputStream( thread.getSocket().getOutputStream() );
				oos.writeObject(message);
			}
			
		} else if(message.getMesType()==MessageType.MESSAGE_GROUP_MES) {
			
			String senderID=message.getSender();
			
			for(String receiverID:ServerThreadManager.serverOnlineUsers()) {
				if(!receiverID.equals(senderID)) {

					ServerConnectClientThread thread=ServerThreadManager.getClientThreadByID(receiverID);
					if(thread!=null) {
						ObjectOutputStream oos = new ObjectOutputStream( thread.getSocket().getOutputStream() );
						oos.writeObject(message);
					}
				}
			}
		} else if(message.getMesType()==MessageType.MESSAGE_FILE_MES) {
			String receriverID=message.getReceiver();
			ServerConnectClientThread thread=ServerThreadManager.getClientThreadByID(receriverID);
			System.out.println(message.getSender()+" send to "+message.getReceiver());
			if(thread!=null) {
				ObjectOutputStream oos = new ObjectOutputStream( thread.getSocket().getOutputStream() );
				oos.writeObject(message);
			}
			
		}
		else {
			System.out.println("other");
		}
	}
	
	public Socket getSocket() {
		return socket;
	}
}
