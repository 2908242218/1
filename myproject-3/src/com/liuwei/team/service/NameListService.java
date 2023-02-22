package com.liuwei.team.service;

import static com.liuwei.team.service.Data.*;

import com.liuwei.team.domain.*;



public class NameListService {
	private Employee[] employees ;

	public NameListService() {
		
		employees = new Employee[EMPLOYEES.length];
		
		for(int i = 0; i < employees.length; i++) {
			
			int type = Integer.parseInt(EMPLOYEES[i][0]);
			int id = Integer.parseInt(EMPLOYEES[i][1]);
			String name = EMPLOYEES[i][2];
			int age = Integer.parseInt(EMPLOYEES[i][3]);
			double salary = Double.parseDouble(EMPLOYEES[i][4]);
			
			Equipment equipment ;
			double bonus;
			switch(type) {
			
			case EMPLOYEE:
				employees[i] = new Employee(id, name, age, salary);
				break;
			case PROGRAMMER:
				equipment = MakeEquipment(i);
				employees[i] = new Programmer(id,name,age,salary,equipment);
				break;
			case DESIGNER:
				equipment = MakeEquipment(i);
				 bonus = Double.parseDouble(EMPLOYEES[i][5]);
				employees[i] = new Designer(id,name,age,salary,equipment,bonus);
				break;
			case ARCHITECT:
				equipment = MakeEquipment(i);
				bonus = Double.parseDouble(EMPLOYEES[i][5]);
				int stock = Integer.parseInt(EMPLOYEES[i][6]);
				employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
				break;
			}
		}
	}
	public Equipment MakeEquipment(int index) {
		int key = Integer.parseInt(EQUIPMENTS[index][0]);
		String modelOrName = EQUIPMENTS[index][1];
		switch(key) {
		case PC:
			String display = EQUIPMENTS[index][2];
			return new PC(modelOrName,display);
		case PRINTER:
			String type = EQUIPMENTS[index][2];
			return new Printer(modelOrName,type);
		case NOTEBOOK:
			double price = Double.parseDouble(EQUIPMENTS[index][2]);
			return new NoteBook(modelOrName,price);
		}
		return null;
	}
	/**
	 * 
	 * @Description 获取全部对象
	 * @author Mikecoder
	 * @date 2022年10月1日下午4:48:14
	 * @return
	 */
	public Employee[] getAllEmployees() {
		return employees;
	}
	/**
	 * 
	 * @Description 获取指定对象
	 * @author Mikecoder
	 * @date 2022年10月1日下午4:48:43
	 * @param id
	 * @return
	 * @throws TeamException
	 */
	public Employee getEmployee(int id) throws TeamException {
		for(int i = 0;i < employees.length;i++) {
			if(employees[i].getId() == id) {
				return employees[i];
			}
		}
		throw new TeamException("找不到该员工");
	}
	
}
