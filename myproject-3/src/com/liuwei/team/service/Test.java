package com.liuwei.team.service;

import com.liuwei.team.domain.Employee;

public class Test {
	public static void main(String[] args) {
		NameListService n  = new NameListService();
		for(int i = 0;i < n.getAllEmployees().length;i++) {
			System.out.println(n.getAllEmployees()[i]);
		}
	}
}
