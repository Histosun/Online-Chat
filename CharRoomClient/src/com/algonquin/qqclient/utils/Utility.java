package com.algonquin.qqclient.utils;

/**
 * 
 * 
工具类的作用:
处理各种情况的用户输入，并且能够按照程序员的需求，得到用户的控制台输入。
*/

import java.util.*;


/**
 * Function
 * handle various of user inpupt, and get input content based on programmer's setting
 * @author Shunping Han
 *
 */
public class Utility {
	//静态属性。。。
	private static Scanner scanner = new Scanner(System.in);
	
	
	/**
	 * 
	 * @return 1——5
	 */
	public static char readMenuSelection() {
	    char c;
	    for (; ; ) {
	        String str = readKeyBoard(1, false);
	        c = str.charAt(0);
	        if (c != '1' && c != '2' && 
	            c != '3' && c != '4' && c != '5') {
	            System.out.print("Wrong option, please choose again!");
	        } else break;
	    }
	    return c;
	}
	
	/**
	 * read a char from keyboard
	 * @return a char
	 */
	public static char readChar() {
	    String str = readKeyBoard(1, false);//a char
	    return str.charAt(0);
	}
	/**
	 * 功能：读取键盘输入的一个字符，如果直接按回车，则返回指定的默认值；否则返回输入的那个字符
	 * @param defaultValue 指定的默认值
	 * @return 默认值或输入的字符
	 */
	
	public static char readChar(char defaultValue) {
	    String str = readKeyBoard(1, true);//要么是空字符串，要么是一个字符
	    return (str.length() == 0) ? defaultValue : str.charAt(0);
	}
	
	/**
	 * 功能：读取键盘输入的整型，长度小于2位
	 * @return 整数
	 */
	public static int readInt() {
	    int n;
	    for (; ; ) {
	        String str = readKeyBoard(10, false);//一个整数，长度<=10位
	        try {
	            n = Integer.parseInt(str);//将字符串转换成整数
	            break;
	        } catch (NumberFormatException e) {
	            System.out.print("数字输入错误，请重新输入：");
	        }
	    }
	    return n;
	}
	/**
	 * 功能：读取键盘输入的 整数或默认值，如果直接回车，则返回默认值，否则返回输入的整数
	 * @param defaultValue 指定的默认值
	 * @return 整数或默认值
	 */
	public static int readInt(int defaultValue) {
	    int n;
	    for (; ; ) {
	        String str = readKeyBoard(10, true);
	        if (str.equals("")) {
	            return defaultValue;
	        }
			
			//异常处理...
	        try {
	            n = Integer.parseInt(str);
	            break;
	        } catch (NumberFormatException e) {
	            System.out.print("数字输入错误，请重新输入：");
	        }
	    }
	    return n;
	}
	
	/**
	 * Read characters of limited length
	 * @param limit length
	 * @return String that is read
	 */
	
	public static String readString(int limit) {
	    return readKeyBoard(limit, false);
	}
	
	public static String readString(int limit, String defaultValue) {
	    String str = readKeyBoard(limit, true);
	    return str.equals("")? defaultValue : str;
	}
	
	
	/**
	 * Read Option
	 * @return Y or N
	 */
	public static char readConfirmSelection() {
	    System.out.println("Please input your option (Y/N): choose carefully");
	    char c;
	    for (; ; ) {
	    	//transfer to upper case
	    	//y => Y n=>N
	        String str = readKeyBoard(1, false).toUpperCase();
	        c = str.charAt(0);
	        if (c == 'Y' || c == 'N') {
	            break;
	        } else {
	            System.out.print("Wrong option, please choose again!");
	        }
	    }
	    return c;
	}
	
	/**
	 * read a String
	 * @param limit length you want
	 * @param blankReturn true, can read blank line
	 * 					  false, cannot read blank line
	 * 			
	 *
	 * @return String that is read
	 */
	private static String readKeyBoard(int limit, boolean blankReturn) {
	    
		String line = "";
	
		//scanner.hasNextLine() if there is next line
	    while (scanner.hasNextLine()) {
	        line = scanner.nextLine();//read the line
	       
			//if user input nothing return
			if (line.length() == 0) {
	            if (blankReturn) return line;//if blankReturn==true, blank line is accepted
	            else continue; //else input again
	        }
	
			//if input length exceed length, input again
			//else input accept
	        if (line.length() < 1 || line.length() > limit) {
	            System.out.print("input length cannot exceed " + limit + " please input again");
	            continue;
	        }
	        break;
	    }
	
	    return line;
	}
}

