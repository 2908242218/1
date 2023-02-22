package com.liuwei.preparedstatement.curd;

import com.liuwei.util.JdbcUtil;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author MikeCoder
 * @create 2023-02-0120:58
 * @description:curd
 * @verson:
 */
public class PreparedStatementTest {
    @Test
    public void preparedStatementTest1() {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            //1.读取四个基本信息
            InputStream rs = PreparedStatementTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
            Properties properties = new Properties();
            properties.load(rs);
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClass");
            //2.加载驱动
            Class.forName(driverClass);
            //3.获取连接
            connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
            //4.预编译sql语句，返回PreparedStatement的实例
            String sql = "INSERT INTO depat02(department_id,department_name)VALUES(?,?);";
            statement = connection.prepareStatement(sql);
            //5.填充占位符
            statement.setInt(1,300);
            statement.setString(2,"dashuwangzi02");
            //6.执行操作
            statement.execute();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //7.资源的关闭
            try {
                if(connection != null){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if(statement != null)
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
    //修改表数据的操作
    @Test
    public void testUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.创建一个连接
            conn = JdbcUtil.getConnection();
            //2.预编译sql语句，返回PreparedStatement实例
            String sql = "update depat02 set department_name = ? where department_id = ?";
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            ps.setObject(1,"kuailejiazu");
            ps.setObject(2,300);
            //4.执行操作
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //5.关闭Connection连接和PreparedStatement连接
            JdbcUtil.closeResource(conn,ps);
        }
    }
    //针对于增删改的通用操作测试
    @Test
    public void testCommonUpdate(){
        //删除测试
//        String sql = "delete from depat02 where department_id = ?";
//        try {
//            update(sql,201);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //修改测试
//        String sql = "update depat02 set department_name = ? where department_id = ?";
//        try {
//            update(sql,"wangzi",200);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        //增加测试
        String sql = "insert into depat02(department_id,department_name) values(?,?)";
        try {
            update(sql,300,"dashu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test//同时插入多条数据的操作
    public void testInert()  {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入cust_id:");
        int id = scanner.nextInt();
        System.out.print("请输入cust_name:");
        String name = scanner.next();
        System.out.print("请输入email:");
        String email = scanner.next();
        System.out.print("请输入birth:");
        String birth = scanner.next();
        String sql = "insert into customers values(?,?,?,?)";
        int insertColumn = update(sql, id, name, email, birth);
        if(insertColumn > 0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }


    }

    //增删改的通用操作方法
    public static int update(String sql,Object ...args) {
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
