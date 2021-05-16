package com.algonquin.qqclient.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.print.attribute.SetOfIntegerSyntax;
import com.algonquin.qqcommon.Message;
import com.algonquin.qqcommon.MessageType;

/**
 * 
 * @author Shi Zhaoyang
 * 
 */

public class ClientConnectServerThread extends Thread{
	private Socket socket;
	
	public ClientConnectServerThread(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket=socket;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	public void run() {
		while(true) {
			try {
				System.out.println("Client thread starts, waiting for messages from server");
				ObjectInputStream ois=new ObjectInputStream(socket.getInputStream());
				System.out.println("got message from server");
				/*
				 * 
				 */
				Message message = (Message) ois.readObject(); 
				
				messageHandler(message);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void messageHandler(Message message) {
		if(message.getMesType() 
				== MessageType.MESSAGE_RET_ONLINEUSERS) {
			System.out.println(message.getContent());
			String[] onlineUsers=message.getContent().split(" ");
			
			System.out.println("==============Online users are as follows==============");
			
			for(int i=0;i < onlineUsers.length;++i) {
				//System.out.println(onlineUsers[i]);
			}
		} else {
			System.out.println("other message type. to be continued.");
		}
	}
}
