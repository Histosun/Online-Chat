package com.algonquin.qqserver.service;

import java.util.HashMap;
import java.util.Set;

public class ServerThreadManager {
	private static HashMap<String, ServerConnectClientThread> connectThreadManager=new HashMap<>();
	
	public static void addClientThread(String userID, ServerConnectClientThread connectThread) {
		connectThreadManager.put(userID, connectThread);
	}
	
	public static ServerConnectClientThread getClientThreadByID(String userID) {
		return connectThreadManager.get(userID);
	}
	
	public static Set<String> serverOnlineUsers() {
		return connectThreadManager.keySet();
	}
	
	public static void removeOnlineClientThread(String userID) {
		connectThreadManager.remove(userID);
	}
}
