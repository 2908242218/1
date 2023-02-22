package com.liuwei.team.domain;

public class Programmer extends Employee {
	private int memberld;
	private Status status = Status.FREE;
	private Equipment equipment;

	public Programmer() {
		super();
	}

	public Programmer(int id, String name, int age, double salary, Equipment equipment) {
		super(id, name, age, salary);
		this.equipment = equipment;
	}

	public int getMemberld() {
		return memberld;
	}

	public void setMemberld(int memberld) {
		this.memberld = memberld;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Equipment getEquipment() {
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}
	public String toString() {
		return super.toString()+"\t程序员\t"+status+"\t\t\t"+equipment.getDescription();
	}
	public String BasicDetails() {
		return memberld+"/"+getId()+"\t"+getName()+"\t"+getAge()+"\t"+getSalary();
	}
	public String getTeamDetails() {
		return BasicDetails()+"\t程序员";
	}

}
