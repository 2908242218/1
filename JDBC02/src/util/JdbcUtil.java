package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author MikeCoder
 * @create 2023-02-0121:56
 * @description:util 操作数据库的工具类
 * @verson:
 */
public class JdbcUtil {
    /**
     *创建连接的操作
     * @return
     * @throws Exception
     */
    public static Connection getConnection() throws Exception{
        InputStream rs = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(rs);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
        //2.加载驱动
        Class.forName(driverClass);
        //3.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
        return conn;
    }

    /**
     * 获取一个连接，使用druid连接池的升级版
     * @return
     */
    private static DataSource source;
    static {
        try {
            Properties poes= new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties");
            poes.load(is);
            source = DruidDataSourceFactory.createDataSource(poes);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection1() throws SQLException {

        Connection conn = source.getConnection();
        return conn;
    }

    /**
     * 关闭连接和Statement的操作
     * @param conn
     * @param ps
     */
    public static void closeResource(Connection conn, Statement ps){
        //7.资源的关闭
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null)
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public static void closeResource(Connection conn, Statement ps,ResultSet rs){
        //7.资源的关闭
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
