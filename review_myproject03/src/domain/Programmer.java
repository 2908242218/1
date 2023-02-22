package domain;

import utility.Status;

/**
 * @author MikeCoder
 * @create 2023-01-1919:13
 * @description:domain
 * @verson:
 */
public class Programmer extends Employee {
    private int memberid;//用于记录成员加入团队后在团队中地ID
    private Status status = Status.FREE;
    private Equipment equipment;

    public Programmer() {
        super();
    }
    public Programmer(int id,String name,int age,double salary,Equipment equipment){
        super(id,name,age,salary);
        this.equipment = equipment;
    }

    public int getMemberid() {
        return memberid;
    }

    public void setMemberid(int memberid) {
        this.memberid = memberid;
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
}
