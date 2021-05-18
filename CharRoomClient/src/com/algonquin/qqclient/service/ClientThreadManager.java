package com.algonquin.qqclient.service;

import java.util.HashMap;

public class ClientThreadManager {
	private static HashMap<String, ClientConnectServerThread> threadManager=new HashMap<>();
	
	public static void addClientThread(String userID, ClientConnectServerThread ccst) {
		threadManager.put(userID, ccst);
	}
	
	public static ClientConnectServerThread getClientThread(String userID) {
		return threadManager.get(userID); 
	}
}
