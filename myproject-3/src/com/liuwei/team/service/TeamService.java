package com.liuwei.team.service;

import com.liuwei.team.domain.Architect;
import com.liuwei.team.domain.Designer;
import com.liuwei.team.domain.Employee;
import com.liuwei.team.domain.Programmer;
import com.liuwei.team.domain.Status;

/**
 * 
 * @Description 关于开发团队成员的管理：添加，删除等
 * @author Mikecoder Email:2908242218@qq.com
 * @date 2022年10月2日下午4:59:13
 *
 *
 */
public class TeamService {
	private static int counter = 1;// 用来为开发团队新增成员自动生成团队中的唯一ID，即memberld。
	private final int MAX_MEMBER = 5;// 表示开发团队最大成员数
	private Programmer[] team = new Programmer[MAX_MEMBER];// 用来保存当前团队中的各成员对象
	private int total = 0;// 记录团队成员的实际人数

	/**
	 * 
	 * @Description 获取开发团队人员名单
	 * @author Mikecoder
	 * @date 2022年10月2日下午5:07:32
	 * @return
	 */
	public Programmer[] getTeam() {
		Programmer[] team = new Programmer[total];
		for (int i = 0; i < total; i++) {
			team[i] = this.team[i];
		}
		return team;
	}

	/**
	 * 
	 * @Description 添加团队成员
	 * @author Mikecoder
	 * @date 2022年10月2日下午5:08:48
	 * @param e
	 * @throws TeamException
	 */
	public void addMember(Employee e) throws TeamException {
		// 成员已满，无法添加
		if (total >= MAX_MEMBER) {
			throw new TeamException("成员已满，无法添加");
		}
		// 该成员不是开发人员，无法添加
		if (!(e instanceof Programmer)) {
			throw new TeamException("该成员不是开发人员，无法添加");
		}
		// 该员工已在本开发团队中
		for (int i = 0; i < total; i++) {
			if (e.getId() == team[i].getId()) {
				throw new TeamException("该员工已在本开发团队中");
			}
		}
		// 该员工已是某团队成员
		// 该员工正在休假，无法添加
		Programmer p = (Programmer) e;
//		if ("BUSY".equals(p.getStatus().getNAME())) {
//			throw new TeamException("该员工已是某团队成员");
//		} else if ("VOCTION".equals(p.getStatus().getNAME())) {
//			throw new TeamException("该员工正在休假，无法添加");
//		}
		switch(p.getStatus()){
			case BUSY:
				throw new TeamException("该员工已是某团队成员");
			case VOCTION:
				throw new TeamException("该员工正在休假，无法添加");
		}
		// 团队中至多只能有一名架构师
		// 团队中至多只能有两名设计师
		// 团队中至多只能有三名程序员
		int numArc = 0, numDes = 0, numPro = 0;
		for (int i = 0; i < total; i++) {
			if (team[i] instanceof Architect) {
				numArc++;
			} else if (team[i] instanceof Designer) {
				numDes++;
			} else if (team[i] instanceof Programmer) {
				numPro++;
			}
			if (p instanceof Architect) {
				if (numArc >= 1) {
					throw new TeamException("团队中至多只能有一名架构师");
				}
			}
			if (p instanceof Designer) {
				if (numDes >= 2) {
					throw new TeamException("团队中至多只能有两名设计师");
				}
			}
			if (p instanceof Programmer) {
				if (numPro >= 3) {
					throw new TeamException("团队中至多只能有三名程序员");
				}
			}
		}
		team[total++] = p;
		p.setStatus(Status.BUSY);
		p.setMemberld(counter++);

	}

	/**
	 * 
	 * @Description 删除指定成员
	 * @author Mikecoder
	 * @date 2022年10月2日下午8:58:29
	 * @param memberld
	 * @throws TeamException
	 */
	public void removeMember(int memberld) throws TeamException {
		int i;
		for (i = 0; i < total; i++) {
			if (team[i].getMemberld() == memberld) {
				team[i].setStatus(Status.FREE);
				break;
			}
		}
		// 当未找到指定memberld的成员时，抛出异常
		if (i == total) {
			throw new TeamException("找不到指定memberld的员工，删除失败");
		}
		// 让数组前一个赋值为后一个的值
		for (int j = i; j < total - 1; j++) {
			team[j] = team[j + 1];
		}
		// 将最后一个成员置空
		team[--total] = null;
	}

}
