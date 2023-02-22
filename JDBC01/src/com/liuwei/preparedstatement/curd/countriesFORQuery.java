package com.liuwei.preparedstatement.curd;

import com.liuwei.bean.Countries;
import com.liuwei.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author MikeCoder
 * @create 2023-02-0219:42
 * @description:curd 查询具体表数据的操作
 * @verson:
 */
public class countriesFORQuery {

    @Test
    public void testQuery() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Countries countries = null;
        try {
            //1.创建连接
            conn = JdbcUtil.getConnection();
            //2.预编译sql语句
            String sql = "select country_id,country_name,region_id from countries where country_id = ?";
            ps = conn.prepareStatement(sql);
            //5.填充占位符
            ps.setObject(1,"AR");
            //4.执行，并返回结果集
            rs = ps.executeQuery();
            //5.获取结果集中的数据
            if(rs.next()){
                String country_id = rs.getString(1);
                String country_name = rs.getString(2);
                int region = rs.getInt(3);

                countries = new Countries(country_id,country_name,region);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            //6.关闭操作
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
        //7.显示结果
        String s = countries.toString();
        System.out.println(s);


    }
}
