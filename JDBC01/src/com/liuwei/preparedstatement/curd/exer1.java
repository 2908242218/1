package com.liuwei.preparedstatement.curd;

import com.liuwei.bean.Student;
import com.liuwei.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @author MikeCoder
 * @create 2023-02-0718:15
 * @description:curd
 * @verson:
 */
public class exer1 {
    @Test
    /**
     * 完成学生信息的删除功能
     */
    public void test4(){
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入学生的考号：");
        String examCard = scan.next();
        String sql = "select* from examstudent where ExamCard = ?";
        Student instance = PreparedStatementFORQuery.getInstance(Student.class, sql, examCard);
        if(instance!=null){
            sql = "delete from examstudent where ExamCard = ?";
            int update = PreparedStatementTest.update(sql, examCard);
            if(update>=1){
                System.out.println("删除成功");
            }
        }else{
            System.out.println("查无此人，请重新输入！");
        }
    }
    @Test
    /**
     * 输入身份证号或准考证号可以查询到学生的基本信息
     */
    public void test3(){
        Scanner scan = new Scanner(System.in);
        System.out.println("请选择您要输入的类型：\na:准考证号\nb:身份证号");
        String option = scan.next();
        char o = option.charAt(0);
        if(o == 'a') {
            System.out.print("请输入准考证号：");
            String examCard = scan.next();
            String sql = "select* from examstudent where ExamCard = ?";
            Student instance = PreparedStatementFORQuery.getInstance(Student.class, sql, examCard);
            if(instance!=null){
                System.out.println("|========查询结果========");
                System.out.println(instance.toString());
            }else{
                System.out.println("查无此人！请重新进入程序");
                return;
            }
        }else if(o == 'b'){
            System.out.print("请输入身份证号：");
            String IDCard = scan.next();
            String sql = "select* from examstudent where IDCard = ?";
            Student instance = PreparedStatementFORQuery.getInstance(Student.class, sql, IDCard);
            if(instance!=null){
                System.out.println("|========查询结果========");
                System.out.println(instance.toString());
            }else{
                System.out.println("查无此人！请重新进入程序");
                return;
            }
        }else{
            System.out.println("您的输入有误！请重新进入程序。");
        }
    }
    @Test
    /**
     * 插入一个新的student信息
     */
    public void test2(){
        //插入一个Student信息
        Scanner scann = new Scanner(System.in);
        System.out.println("请输入考生的详细信息\n");
        System.out.print("Type:");
        int type = scann.nextInt();
        System.out.print("IDCard:");
        String idCard = scann.next();
        System.out.print("ExamCard:");
        String examCard = scann.next();
        System.out.print("StudentName:");
        String studentName = scann.next();
        System.out.print("Location:");
        String location = scann.next();
        System.out.print("Grade:");
        int grade = scann.nextInt();
        String sql = "insert into examstudent(Type,IDCard,ExamCard,StudentName,Location,Grade) values(?,?,?,?,?,?)";
        int update = update(sql, type, idCard, examCard, studentName, location, grade);
        if(update>=1){
            System.out.println("信息录入成功！");
        }else{
            System.out.println("信息录入失败");
        }

    }
    @Test
    public void test(){
        Scanner scann = new Scanner(System.in);
        System.out.print("cust_id=");
        int id = scann.nextInt();
        System.out.print("cust_name=");
        String name = scann.next();
        System.out.print("email=");
        String email = scann.next();
        System.out.print("birth=");
        String birth = scann.next();
        String sql = "insert into customers values(?,?,?,?)";
        int update = update(sql, id, name, email, birth);
        System.out.println(update);
    }


    public int update(String sql,Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.创建一个连接
            conn = JdbcUtil.getConnection();
            //2.预编译sql语句，返回PreparedStatement实例
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //4.执行操作
            int i = ps.executeUpdate();
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{

            //5.关闭Connection连接和PreparedStatement连接
            JdbcUtil.closeResource(conn,ps);
        }
        return 0;

    }
}
