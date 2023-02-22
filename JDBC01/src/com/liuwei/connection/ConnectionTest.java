package com.liuwei.connection;

import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author MikeCoder
 * @create 2023-01-3016:37
 * @description:connection
 * @verson:
 */
public class ConnectionTest {
    //方式一
    @Test
    public void testConnection1() throws SQLException {
        //1.创建一个Driver引擎
        Driver driver = new com.mysql.cj.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/atguigudb";//查找路径
        //jdbc:mysql:协议
        //localhost:ip地址
        //3306:端口号
        //atguigudb:数据库
        Properties info = new Properties();//数据库用户和密码
        info.setProperty("user","root");
        info.setProperty("password","3230542427Lw$");
       Connection conn = driver.connect(url, info);//创建一个连接
        System.out.println(conn);
    }
    //方式二：使用泛型来创建Driver引擎
    @Test
    public void testConnection2() throws Exception{
        //1.使用泛型创建Driver
        Class  aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver)aClass.newInstance();
        //提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/atguigudb";
        //提供数据库用户名和密码
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","3230542427Lw$");
        //创建连接
        Connection conn = driver.connect(url,properties);
        System.out.println(conn);
    }
    //方式三：使用DriverManager来创建连接
    @Test
    public void testConnection3() throws Exception{
        //创建一个Driver驱动
        Class aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver)aClass.newInstance();
        //提供需要访问的数据库、数据库用户名、数据库密码
        String url = "jdbc:mysql://localhost:3306/atguigudb";
        String user = "root";
        String password = "3230542427Lw$";
        //注册驱动
        DriverManager.registerDriver(driver);
        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
    //方式四：可用直接加载驱动，不用显示的注册驱动
    @Test
    public void testConnection4() throws Exception{
        //1,提供需要访问的数据库、数据库用户名、数据库密码
        String url = "jdbc:mysql://localhost:3306/atguigudb";
        String user = "root";
        String password = "3230542427Lw$";
        //2.加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");//Driver类中有静态代码块，加载类的同时实现了注册驱动
        //3.创建连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
    //方式五（final版）：将数据库连接需要的4个基本信息声明在配置文件中，通过读取配置文件的方式，获取连接
    //好处
    //1.实现类代码与数据的分离，实现了解耦。
    //2.只需要修改配置文件信息，可以避免程序重新打包。
    @Test
    public void testConnection5() throws Exception{
        //1.读取配置文件中的4个基本信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pro = new Properties();
        pro.load(is);
        String user = pro.getProperty("user");
        String password = pro.getProperty("password");
        String url = pro.getProperty("url");
        String driverClass = pro.getProperty("driverClass");
        //2.加载驱动
        Class.forName(driverClass);
        //3.获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);


    }
}
