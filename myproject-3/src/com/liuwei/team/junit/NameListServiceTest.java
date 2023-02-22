package com.liuwei.team.junit;

import org.junit.Test;

import com.liuwei.team.domain.Employee;
import com.liuwei.team.service.NameListService;
import com.liuwei.team.service.TeamException;

/**
 * 
 * @Description
 * @author Mikecoder  Email:2908242218@qq.com
 * @date 2022年10月1日下午10:07:50
 *
 *
 */
public class NameListServiceTest {
	@Test
	public void testGetAllEmployees() {
		NameListService service = new NameListService();
		Employee[] employees = service.getAllEmployees();
		for(int i = 0;i < employees.length;i++ ) {
			System.out.println(employees[i]);
		}
	}
	@Test
	public void testGetEmployee() {
		NameListService service = new NameListService();
		int id = 56;
		try {
			Employee em = service.getEmployee(id);
			System.out.println(em);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
	}
}
