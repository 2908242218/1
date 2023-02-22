package com.liuwei.team.view;

import java.util.*;
/**
 * 
 * @Description 项目中提供了TSUtility.java类，可用来方便地实现键盘访问。
 * @author Mikecoder  Email:2908242218@qq.com
 * @date 2022年9月30日上午8:55:03
 *
 *
 */
public class TSUitility {
	private static Scanner scanner = new Scanner(System.in);
	/**
	 * 
	 * @Description 该方法读取键盘，如果用户键入‘1’-‘4’中任意字符，则方法返回，返回值为用户键入字符。
	 * @author Mikecoder
	 * @date 2022年9月30日上午9:01:26
	 * @return
	 */
	public static char readMenuSelection() {
		char a;
		for(;;) {
			String str = readKeyBoard(1,false);
			a = str.charAt(0);
			if(a != '1' && a != '2' && a != '3' && a != '4') {
				System.out.print("选择错误，请重新输入：");
			}else {
				break;
			}
		}
		return a;
	}
	/**
	 * 
	 * @Description 该方法提示并等待，直到用户按回车键后返回。
	 * @author Mikecoder
	 * @date 2022年9月30日上午9:03:19
	 */
	public static void readReturn() {
		System.out.println("按回车键继续...");
		readKeyBoard(100,true);
	}
	/**
	 * 
	 * @Description 该方法从键盘读取一个长度不超过2位的整数，并将其作为方法的返回值。
	 * @author Mikecoder
	 * @date 2022年9月30日上午9:04:14
	 * @return
	 */
	public static int readInt() {
		int n;
		for(;;) {
			String str = readKeyBoard(2,false);
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
	 * 
	 * @Description 从键盘读取‘Y’或‘N’，并将其作为方法的返回值。
	 * @author Mikecoder
	 * @date 2022年9月30日上午9:05:36
	 * @return
	 */
	public static char readComfirmSelection() {
		char c;
		for(;;) {
			String str = readKeyBoard(1,false).toUpperCase();
			c = str.charAt(0);
			if(c == 'Y' || c == 'N') {
				break;
			}else {
				System.out.println("选择错误，请重新输入：");
			}
		}
		return c;
	}
	private static String readKeyBoard(int limit,boolean blankReturn) {
		String line = "";
		while(scanner.hasNextLine()) {
			line = scanner.nextLine();
			if(line.length() == 0) {
				if(blankReturn) {
					return line;
				}else continue;
			}
			if(line.length() < 1 || line.length() > limit) {
				System.out.print("输入长度（不大于"+limit+"）错误，请重新输入：");
				continue;
			}
			break;
		}
		return line;
	}
}
