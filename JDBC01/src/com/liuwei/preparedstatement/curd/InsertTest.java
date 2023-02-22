package com.liuwei.preparedstatement.curd;

import com.liuwei.util.JdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author MikeCoder
 * @create 2023-02-1621:04
 * @description:crud
 * @verson:
 */
public class InsertTest {
    //批量插入数据方式一：43094
    @Test
    public void test1()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            long start = System.currentTimeMillis();
            for(int i = 1;i<=20000;i++){
                ps.setString(1,"name_"+i);
                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为："+(end-start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn,ps);
        }
    }
    //prepareStatement批量插入数据方式二：682 2281
    @Test
    public void test2()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            long start = System.currentTimeMillis();
            for(int i = 1;i<=100000;i++){
                ps.setString(1,"name_"+i);
                //"赞"sql
                ps.addBatch();
                if(i%500 == 0) {
                    //执行Batch
                    ps.executeBatch();
                    //清空Batch
                    ps.clearBatch();
                }
            }
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为："+(end-start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn,ps);
        }

    }
    //prepareStatement批量插入数据方式三：540 1788
    @Test
    public void test3()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JdbcUtil.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            long start = System.currentTimeMillis();
            conn.setAutoCommit(false);
            for(int i = 1;i<=100000;i++){
                ps.setString(1,"name_"+i);
                //"赞"sql
                ps.addBatch();
                if(i%500 == 0) {
                    //执行Batch
                    ps.executeBatch();
                    //清空Batch
                    ps.clearBatch();
                }
            }
            conn.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为："+(end-start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn,ps);
        }

    }
}
