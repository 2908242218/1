package com.liuwei.bean;

/**
 * @author MikeCoder
 * @create 2023-02-0921:01
 * @description:bean
 * @verson:
 */
public class Student {
    private int FlowID;
    private int Type;
    private String IDCard;
    private String ExamCard;
    private String StudentName;
    private String Location;
    private int Grade;

    public Student() {
    }

    public Student(int flowID, int type, String IDCard, String examCard, String studentName, String location, int grade) {
        FlowID = flowID;
        Type = type;
        this.IDCard = IDCard;
        ExamCard = examCard;
        StudentName = studentName;
        Location = location;
        Grade = grade;
    }

    public int getFlowID() {
        return FlowID;
    }

    public void setFlowID(int flowID) {
        FlowID = flowID;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getExamCard() {
        return ExamCard;
    }

    public void setExamCard(String examCard) {
        ExamCard = examCard;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getGrade() {
        return Grade;
    }

    public void setGrade(int grade) {
        Grade = grade;
    }

    @Override
    public String toString() {
        return "流水号：\t"+FlowID+"\n四级/六级："+Type+"\n身份证号："+IDCard+"\n准考证号："+ExamCard+"\n学生姓名："
                +StudentName+"\n区域:\t"+Location+"\n成绩：\t"+Grade;
    }
}
