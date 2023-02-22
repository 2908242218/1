package com.liuwei.preparedstatement.curd;

import com.liuwei.bean.Countries;
import com.liuwei.bean.JobGrades;
import com.liuwei.util.JdbcUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author MikeCoder
 * @create 2023-02-0521:32
 * @description:curd
 * @verson:
 */
public class PreparedStatementFORQuery {
    @Test
    public void test1(){
        String sql = "select grade_level gradeLevel,lowest_sal lowestSal,highest_sal highestSal from job_grades where lowest_sal <= ?";
        List<JobGrades> list = getFORList(JobGrades.class, sql, 15000);
        list.forEach(System.out ::println);
    }

    public <T> List<T> getFORList(Class<T> clazz,String sql,Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //获取连接
            conn = JdbcUtil.getConnection();
            //预编译
            ps = conn.prepareStatement(sql);
            //填充占位符
            for(int i = 0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //执行获得结果集中
            rs = ps.executeQuery();
            //获取结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获取列数
            int columnCount = rsmd.getColumnCount();
            //创建集合对象
            ArrayList<T> list = new ArrayList<>();
            //为查询到的每一个字段创建对象并为属性赋值
            while(rs.next()){
                T t = clazz.newInstance();
                for(int i = 0;i<columnCount;i++){
                    //获得列值
                    Object columnValue = rs.getObject(i + 1);
                    //获得列别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //为属性赋值
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.closeResource(conn,ps,rs);
        }
        return null;


    }
    @Test
    public void test(){
        String sql = "select * from countries where country_id = ?";
        Countries countries = getInstance(Countries.class, sql, "AR");
        System.out.println(countries);

        sql = "select grade_level gradeLevel,lowest_sal lowestSal,highest_sal highestSal from job_grades where grade_level = ?";
        JobGrades jobGrades = getInstance(JobGrades.class, sql, "A");
        System.out.println(jobGrades);
    }
    /**
     * 根据查询的不同表，返回不同对象
     * @param <T>
     * @return
     */
    public static <T> T getInstance(Class<T> clazz,String sql,Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //创建连接
            conn = JdbcUtil.getConnection();
            //预编译
            ps = conn.prepareStatement(sql);
            //填充占位符
            for(int i = 0;i<args.length;i++){
                ps.setObject(i+1,args[i]);
            }
            //执行，产生结果集
            rs = ps.executeQuery();
            //获得结果集元数据
            ResultSetMetaData rsmd = rs.getMetaData();
            //获得结果集列数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                T t = clazz.newInstance();
                for(int i = 0;i<columnCount;i++){
                    //获取列的值
                    Object columnValue = rs.getObject(i + 1);
                    //获取列的别名
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //将获取到的值付给对象与别名一致的属性
                    Field field = t.getClass().getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,columnValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.closeResource(conn,ps,rs);
        }
        return null;

    }
}
