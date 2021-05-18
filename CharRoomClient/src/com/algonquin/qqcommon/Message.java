package com.algonquin.qqcommon;

import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID=1L;
	
	private String sender;
	private String receiver;
	private String content;
	private String sendTime;
	private int mesType;
	
	byte[] fileBytes;
	
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getReceiver() {
		return receiver;
	}
	
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getSendTime() {
		return sendTime;
	}
	
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	public int getMesType() {
		return mesType;
	}
	
	public void setMesType(int mesType) {
		this.mesType=mesType;
	}
	
	public void setFileBytes(byte[] fileBytes) {
		this.fileBytes=fileBytes;
	}
	
	public byte[] getFileBytes() {
		return fileBytes;
	}
}
