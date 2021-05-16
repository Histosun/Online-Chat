package com.algonquin.qqclient.utils;

/**
 * 
 * 
�����������:
�������������û����룬�����ܹ����ճ���Ա�����󣬵õ��û��Ŀ���̨���롣
*/

import java.util.*;


/**
 * Function
 * handle various of user inpupt, and get input content based on programmer's setting
 * @author Shunping Han
 *
 */
public class Utility {
	//��̬���ԡ�����
	private static Scanner scanner = new Scanner(System.in);
	
	
	/**
	 * 
	 * @return 1����5
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
	 * ���ܣ���ȡ���������һ���ַ������ֱ�Ӱ��س����򷵻�ָ����Ĭ��ֵ�����򷵻�������Ǹ��ַ�
	 * @param defaultValue ָ����Ĭ��ֵ
	 * @return Ĭ��ֵ��������ַ�
	 */
	
	public static char readChar(char defaultValue) {
	    String str = readKeyBoard(1, true);//Ҫô�ǿ��ַ�����Ҫô��һ���ַ�
	    return (str.length() == 0) ? defaultValue : str.charAt(0);
	}
	
	/**
	 * ���ܣ���ȡ������������ͣ�����С��2λ
	 * @return ����
	 */
	public static int readInt() {
	    int n;
	    for (; ; ) {
	        String str = readKeyBoard(10, false);//һ������������<=10λ
	        try {
	            n = Integer.parseInt(str);//���ַ���ת��������
	            break;
	        } catch (NumberFormatException e) {
	            System.out.print("��������������������룺");
	        }
	    }
	    return n;
	}
	/**
	 * ���ܣ���ȡ��������� ������Ĭ��ֵ�����ֱ�ӻس����򷵻�Ĭ��ֵ�����򷵻����������
	 * @param defaultValue ָ����Ĭ��ֵ
	 * @return ������Ĭ��ֵ
	 */
	public static int readInt(int defaultValue) {
	    int n;
	    for (; ; ) {
	        String str = readKeyBoard(10, true);
	        if (str.equals("")) {
	            return defaultValue;
	        }
			
			//�쳣����...
	        try {
	            n = Integer.parseInt(str);
	            break;
	        } catch (NumberFormatException e) {
	            System.out.print("��������������������룺");
	        }
	    }
	    return n;
	}
	
	/**
	 * ���ܣ���ȡ���������ָ�����ȵ��ַ���
	 * @param limit ���Ƶĳ���
	 * @return ָ�����ȵ��ַ���
	 */
	
	public static String readString(int limit) {
	    return readKeyBoard(limit, false);
	}
	
	/**
	 * ���ܣ���ȡ���������ָ�����ȵ��ַ�����Ĭ��ֵ�����ֱ�ӻس�������Ĭ��ֵ�����򷵻��ַ���
	 * @param limit ���Ƶĳ���
	 * @param defaultValue ָ����Ĭ��ֵ
	 * @return ָ�����ȵ��ַ���
	 */
	
	public static String readString(int limit, String defaultValue) {
	    String str = readKeyBoard(limit, true);
	    return str.equals("")? defaultValue : str;
	}
	
	
	/**
	 * ���ܣ���ȡ���������ȷ��ѡ�Y��N
	 * ��С�Ĺ��ܣ���װ��һ��������.
	 * @return Y��N
	 */
	public static char readConfirmSelection() {
	    System.out.println("���������ѡ��(Y/N): ��С��ѡ��");
	    char c;
	    for (; ; ) {//����ѭ��
	    	//����������ܵ��ַ���ת���˴�д��ĸ
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

