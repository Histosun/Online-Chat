package com.algonquin.qqcommon;

import java.io.Serializable;

public class Message implements Serializable{
	private static final long serialVersionUID=1L;
	
	private int sender;
	private int receiver;
	private String content;
	private String sendTime;
	private int mesType;
	
	public int getSender() {
		return sender;
	}
	
	public void setSender(int sender) {
		this.sender = sender;
	}
	
	public int getReceiver() {
		return receiver;
	}
	
	public void setReceiver(int receiver) {
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
}
