package com.algonquin.qqclient.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import com.algonquin.qqcommon.Message;
import com.algonquin.qqcommon.MessageType;
import com.algonquin.qqcommon.User;

/**
 * 
 * @author Shi Zhaoyang
 * verify user when log in
 */

public class UserClientService {
	private User u=new User();
	private Socket socket;
	
	public boolean logIn(String userId,String pwd) {
		u.setUserName(userId);
		u.setPasswd(pwd);
		
		try {
			socket = new Socket(InetAddress.getByName("127.0.0.1"), 9999);
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
			
			oos.writeObject(u);
			
			ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
			Message ms=(Message)ois.readObject();
			
			if(ms.getMesType()==MessageType.MESSAGE_LOGIN_SUCCEED) {
				ClientConnectServerThread ccst = new ClientConnectServerThread(socket);
				ccst.start();
				ClientThreadManager.addClientThread(userId, ccst);
				
				return true;
			} else {
				oos.close();
				ois.close();
				socket.close();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public void getOnlineUsers() {
		
		Message message=new Message();
		message.setMesType(MessageType.MESSAGE_GET_ONLINEUSERS);
			
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(ClientThreadManager.getClientThread(u.getUserName()).getSocket().getOutputStream());
			
			oos.writeObject(message);
			System.out.println("sending message to server for onlineusers");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void logout() {
		Message message=new Message();
		
		message.setMesType(MessageType.MESSAGE_CLIENT_EXIT);
		
		try {
			
			ObjectOutputStream oos = new ObjectOutputStream(ClientThreadManager.getClientThread(u.getUserName()).getSocket().getOutputStream());
			
			oos.writeObject(message);
			
			System.exit(0);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
