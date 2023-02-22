package com.liuwei.bean;

/**
 * @author MikeCoder
 * @create 2023-02-0520:22
 * @description:bean
 * @verson:
 */
public class JobGrades {
    private String gradeLevel;
    private int lowestSal;
    private int highestSal;

    public JobGrades() {
    }

    public JobGrades(String gradeLevel, int lowestSal, int highestSal) {
        this.gradeLevel = gradeLevel;
        this.lowestSal = lowestSal;
        this.highestSal = highestSal;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public int getLowestSal() {
        return lowestSal;
    }

    public void setLowestSal(int lowestSal) {
        this.lowestSal = lowestSal;
    }

    public int getHighestSal() {
        return highestSal;
    }

    public void setHighestSal(int highestSal) {
        this.highestSal = highestSal;
    }

    @Override
    public String toString() {
        return "JobGrades{" +
                "gradeLevel='" + gradeLevel + '\'' +
                ", lowestSal=" + lowestSal +
                ", highestSal=" + highestSal +
                '}';
    }
}
