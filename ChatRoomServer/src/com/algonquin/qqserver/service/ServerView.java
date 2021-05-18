package com.algonquin.qqserver.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.algonquin.qqcommon.Message;
import com.algonquin.qqcommon.MessageType;
import com.algonquin.qqcommon.User;

public class ServerView {
	
	public static void main(String[] args){
		new ServerView();
	}
	
	private ServerSocket serverSocket=null;
	private static HashMap<String, User> validUsers=new HashMap<String, User>();
	
	static {
		User u=new User("100","123456");
		validUsers.put(u.getUserName(), u);
		u=new User("Tom","123456");
		validUsers.put(u.getUserName(), u);
		u=new User("Shawn","123456");
		validUsers.put(u.getUserName(), u);
		u=new User("Zhaoyang","123456");
		validUsers.put(u.getUserName(), u);
		u=new User("Jinyu","123456");
		validUsers.put(u.getUserName(), u);
	}
	
	private boolean validateUser(String userID,String passwd){
		
		User u;
		return ( (u=validUsers.get(userID))==null ) ? false : u.getPasswd().equals(passwd);
	}
	
	
	public ServerView() {
		try {
			serverSocket=new ServerSocket(9999);
			while(true) {
				Socket socket=serverSocket.accept();

				ObjectInputStream ois= new ObjectInputStream(socket.getInputStream());

				User u = (User) ois.readObject();
				Message loginRes=new Message();
				ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream());
				
				if(validateUser(u.getUserName(), u.getPasswd())) {
					System.out.println("User "+u.getUserName()+"logged in");
					loginRes.setMesType(MessageType.MESSAGE_LOGIN_SUCCEED);
					
					oos.writeObject(loginRes);
					
					ServerConnectClientThread scct =
						new ServerConnectClientThread(u.getUserName(), socket);
					scct.start();
					ServerThreadManager.addClientThread(u.getUserName(), scct);
					
				}
				
				else {
					loginRes.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
					
					oos.writeObject(loginRes);
					socket.shutdownOutput();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
