package com.algonquin.qqclient.view;

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
	
	public static void main(String[] args){
		new QQProgram().mainMenu();
	}
	
	private void loginMenu(String userName) {
		while(loop) {
			System.out.println("==========Welcome " + userName + "==========");
			System.out.println("\t\t 1 show online users ");
			System.out.println("\t\t 2 group mail ");
			System.out.println("\t\t 3 private mamil ");
			System.out.println("\t\t 4 file transfer ");
			System.out.println("\t\t 9 log out ");
			key=Utility.readString(1);
			
			switch (key) {
			case "1":
				userClientService.getOnlineUsers();
				break;
				
			case "2":
				break;

			case "3":
				break;

			case "4":
				break;

			case "9":
				userClientService.logout();
				break;
			}
		}
	}
	
	
	//show main menu
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
