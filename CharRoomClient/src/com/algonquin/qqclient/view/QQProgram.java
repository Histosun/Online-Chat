package com.algonquin.qqclient.view;

import com.algonquin.qqclient.service.MessageClientService;
import com.algonquin.qqclient.service.UserClientService;
import com.algonquin.qqclient.utils.Utility;

/**
 * Class that start the client program
 * and show menu
 * @author Shi Zhaoyang
 * 
 */
public class QQProgram {
	private boolean loop = true;//
	private String key="";
	private UserClientService userClientService=new UserClientService();
	private MessageClientService messageClientService=new MessageClientService();
	
	public static void main(String[] args){
		
		new QQProgram().mainMenu();
	}
	
	private void loginMenu(String userName) {
		while(loop) {
			System.out.println("==========Welcome " + userName + "==========");
			System.out.println("\t\t 1 show online users ");
			System.out.println("\t\t 2 group mail ");
			System.out.println("\t\t 3 private mail ");
			System.out.println("\t\t 4 file transfer ");
			System.out.println("\t\t 9 log out ");
			key=Utility.readString(1);
			
			switch (key) {
			case "1":
				userClientService.getOnlineUsers();
				break;
				
			case "2":
				System.out.println("Input ther message you want to send");
				String groupMailContent=Utility.readString(100);
				messageClientService.sendMessageToGroup(groupMailContent, userName);
				break;

			case "3":
				System.out.println("Input ther user you want to chat with");
				String getterID=Utility.readString(50);
				System.out.println("Input ther message you want to send");
				String contentString=Utility.readString(100);
				messageClientService.sendMessageToOne(contentString, userName, getterID);
				break;

			case "4":
				System.out.println("Input ther user you want to send filr to");
				getterID=Utility.readString(50);
				System.out.println("Input the file path");
				String filePath=Utility.readString(100);
				messageClientService.sendFileToOne(filePath, userName, getterID);
				break;

			case "9":
				userClientService.logout();
				break;
			}
		}
	}
	
	
	/**
	 * main thread that will send message to server
	 */
	private void mainMenu() {
		while(loop) {
			System.out.println("==========Welcome log in network communication system==========");
			System.out.println("\t\t 1 log in");
			System.out.println("\t\t 9 exit");
			System.out.println("");
			key=Utility.readString(1);
			
			switch(key) {
			case "1":
				System.out.println("Please input your user name: ");
				String userId=Utility.readString(50);
				System.out.println("Please input your passwd: ");
				String pwd=Utility.readString(50);
				
				if( userClientService.logIn(userId, pwd) ) {
					loginMenu(userId);
				} else {
					System.out.println("=======Fail to log in=======");
				}
				break;
			case "9":
				loop=false;
				break;
			}
		}
	}
}
