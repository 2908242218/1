package com.liuwei.team.view;

import com.liuwei.team.domain.Employee;
import com.liuwei.team.domain.Programmer;
import com.liuwei.team.service.NameListService;
import com.liuwei.team.service.TeamException;
import com.liuwei.team.service.TeamService;

public class TeamView {
	private NameListService listSvc = new NameListService();
	private TeamService teamSvc = new TeamService();

	/**
	 * 
	 * @Description 主界面显示及控制方法
	 * @author Mikecoder
	 * @date 2022年10月2日下午10:55:51
	 */
	public void enterMainMenu() {
		boolean loopFlag = true;
		while (loopFlag) {
			listAllEmployee();
			System.out.print("1-开发团队列表，2-添加团队成员，3-删除团队成员，4-退出：");
			int key = TSUitility.readMenuSelection();
			switch (key) {
			case '1':
				getTeam();
				break;
			case '2':
				addMember();
				break;
			case '3':
				deleteMember();
				break;
			case '4':
				System.out.print("是否确定退出(Y/N):");
				char readComfirmSelection = TSUitility.readComfirmSelection();
				if (readComfirmSelection == 'Y') {
					return;
				}
				break;
			}
			TSUitility.readReturn();
		}
	}

	/**
	 * 
	 * @Description 以表格格式列出公司所有成员
	 * @author Mikecoder
	 * @date 2022年10月2日下午10:56:18
	 */

	private void listAllEmployee() {
		System.out.println("-------------------------员工调度列表------------------------------------");
		System.out.println("id\t姓名\t年龄\t工资\t职位\t工作状态\t奖金\t股票\t设备信息");
		Employee[] allEmployees = listSvc.getAllEmployees();
		for (int i = 0; i < allEmployees.length; i++) {
			System.out.println(allEmployees[i]);
		}
		System.out.println("-------------------------------------------------------------------------");
	}

	/**
	 * 
	 * @Description 显示团队成员列表操作
	 * @author Mikecoder
	 * @date 2022年10月2日下午10:56:53
	 */
	private void getTeam() {
		if (teamSvc.getTeam() == null || teamSvc.getTeam().length == 0) {
			System.out.println("团队列表中未发现成员");
		} else {
			System.out.println("MID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票");
			for (int i = 0; i < teamSvc.getTeam().length; i++) {
				System.out.println(teamSvc.getTeam()[i].getTeamDetails());
			}
		}
	}

	/**
	 * 
	 * @Description 实现添加成员操作
	 * @author Mikecoder
	 * @date 2022年10月2日下午10:57:28
	 */
	private void addMember() {
		System.out.print("请输入要添加成员的id:");
		int id = TSUitility.readInt();
		try {
			teamSvc.addMember(listSvc.getEmployee(id));
		} catch (TeamException e) {
			System.out.println("添加失败，失败原因：" + e.getMessage());
		}
		System.out.println("添加成功");
	}

	/**
	 * 
	 * @Description 实现删除成员操作
	 * @author Mikecoder
	 * @date 2022年10月2日下午10:57:50
	 */
	private void deleteMember() {
		System.out.print("请输入要删除成员的memberld：");
		int memberld = TSUitility.readInt();
		System.out.print("是否确定退出(Y/N):");
		char isConfirmRemove = TSUitility.readComfirmSelection();
		if (isConfirmRemove == 'Y') {
			try {
				teamSvc.removeMember(memberld);
			} catch (TeamException e) {
				System.out.println("删除失败，失败原因：" + e.getMessage());
			}
			System.out.println("删除成功");
		}
	}

	public static void main(String[] args) {
		TeamView t = new TeamView();
		t.enterMainMenu();
	}
}
