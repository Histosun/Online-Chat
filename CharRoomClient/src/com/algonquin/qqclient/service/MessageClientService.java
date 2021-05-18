package com.algonquin.qqclient.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

import com.algonquin.qqcommon.Message;
import com.algonquin.qqcommon.MessageType;

public class MessageClientService {
	/**
	 * send message to one of the user
	 * @param content
	 * @param serderID
	 * @param getterID
	 */
	
	public void sendMessageToOne(String content,String senderID, String getterID) {
		Message message=new Message();
		
		message.setSender(senderID);
		message.setReceiver(getterID);
		message.setContent(content);
		message.setSendTime(new Date().toString());
		message.setMesType(MessageType.MESSAGE_COMM_MES);
		System.out.println(senderID + " told " + getterID + ": " + content);
		
		try {
			ObjectOutputStream oos = 
					new ObjectOutputStream(ClientThreadManager.getClientThread(senderID).getSocket().getOutputStream());
			oos.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * send message to all online users
	 * @param content
	 * @param senderID
	 * @param getterID
	 */
	public void sendMessageToGroup(String content,String senderID) {
		Message message=new Message();
		
		message.setSender(senderID);
		message.setContent(content);
		message.setSendTime(new Date().toString());
		message.setMesType(MessageType.MESSAGE_GROUP_MES);
		System.out.println(senderID + " told everyone: " + content);
		
		try {
			ObjectOutputStream oos = 
					new ObjectOutputStream(ClientThreadManager.getClientThread(senderID).getSocket().getOutputStream());
			oos.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void sendFileToOne(String fileName,String senderID, String getterID) {
		Message message=new Message();
		
		message.setSender(senderID);
		message.setReceiver(getterID);
		message.setSendTime(new Date().toString());
		message.setMesType(MessageType.MESSAGE_FILE_MES);
		
		try(FileInputStream fis=new FileInputStream(fileName)) {
			
			byte[] fileBytes=fis.readAllBytes();
			message.setFileBytes(fileBytes);
			ObjectOutputStream oos = 
					new ObjectOutputStream(ClientThreadManager.getClientThread(senderID).getSocket().getOutputStream());
			oos.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
