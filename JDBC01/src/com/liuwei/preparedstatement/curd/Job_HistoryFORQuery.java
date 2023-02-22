package com.liuwei.preparedstatement.curd;

import com.liuwei.bean.Job_History;
import com.liuwei.util.JdbcUtil;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author MikeCoder
 * @create 2023-02-0515:30
 * @description:curd
 * @verson:
 */
public class Job_HistoryFORQuery {
    @Test
    public void testJob_HistoryFORQuery(){
        String sql = "select * from job_history where employee_id = ?";
        Job_History job_history = job_HistoryCommomFORQuery(sql, 102);
        System.out.println(job_history);
    }
    //通用的查询表的方法
    public Job_History job_HistoryCommomFORQuery(String sql,Object...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //1.获取连接
            conn = JdbcUtil.getConnection();
            //2.生成预编译对象
            ps = conn.prepareStatement(sql);
            //3.填充占位符
            for(int i = 0;i < args.length;i++){
                ps.setObject(i+1,args[i]);
            }
             rs = ps.executeQuery();
            //4.获得结果集
            ResultSetMetaData rsmd = rs.getMetaData();//获取结果集的元数据
            int count = rsmd.getColumnCount();//获取结果集的列数
            if(rs.next()){
                Job_History jh = new Job_History();
                for(int i = 0;i<count;i++){
                    //获得结果集每一列的列名
                    String columnName = rsmd.getColumnName(i + 1);
                    //获取结果集每一列的值
                    Object columnValue = rs.getObject(i + 1);
                    //将获得的结果集的值按列名分别赋值给对象同名属性
                    Field field = jh.getClass().getDeclaredField(columnName);
                    field.setAccessible(true);//设置属性为可访问的
                    field.set(jh,columnValue);
                }
                return jh;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            JdbcUtil.closeResource(conn,ps,rs);
        }
        return null;
    }

}
